<?php

namespace App\Controllers;

use App\Helpers\Acess;
use App\Helpers\Output;
use App\Model\role;
use Exception;
use ArrayObject;

//require 'App\Model\role.php';
//require 'App\Helpers\Acess.php';

class User
{

    protected static array $image = [
        'image/gif',
        'image/jpeg',
        'image/png'
    ];



    public function createUser(): void
    {
        if (!$_POST) {
            header('HTTP/1.1 405');
        }
        $flag=false;

        if($this->verifContain($_POST)) Output::createAlert('erreur contenue non autoriser','danger','index.php?view=View/User/Singup');

        if (is_string($_POST['login']) and is_string($_POST['password']) and is_string($_POST['email']) and
            is_string($_POST['FirstName']) and is_string($_POST['LastName']) and is_string($_POST['Adresse']) and is_string($_POST['tel'])) {
            $flag=true;
        }


        if (!empty($_POST['login']) and !empty($_POST['password']) and !empty($_POST['email']) and !empty($_POST['lang']) and
            !empty($_POST['FirstName']) and !empty($_POST['LastName']) and !empty($_POST['Adresse']) and !empty($_POST['Birthday'])
            and !empty($_POST['Nation'] and !empty($_POST['tel'])) and $flag) {

            $user_data = array(
                'login' => htmlentities(trim($_POST['login'])),
                'password' => htmlentities(password_hash($_POST['password'], PASSWORD_DEFAULT)),
                'email' => htmlentities($_POST['email']),
                'FirstName' => htmlentities($_POST['FirstName']),
                'LastName' => htmlentities($_POST['LastName']),
                'Birthday' => htmlentities($_POST['Birthday']),
                'Adresse' => htmlentities($_POST["Adresse"]),
                'Nation' => htmlentities($_POST['Nation']),
                'NumTel' => htmlentities($_POST['tel']),
                'lang' => htmlentities($_POST["lang"])
            );
            $user = new \App\Model\user();
            if ($user->VerifUserNameOrMail($user_data['login'], $user_data['email'])) {
                $user->CreateUser($user_data);
                $role = new Role();
                $role->GiveRoleByUserName($user_data['login'], 4);
                $userId = $user->GetUserID($user_data['login']);
                if (!empty($_FILES['photo']['name'])) $this->UploadImage($userId);

                $user_data['idUser'] =  $userId;
                $userProfile = $this->formatProfile($user_data, true);
                $user->SetLastLogin($userId);


                Output::createAlert("Compte cree", 'success', 'index.php?view=View/User/Login');

            } else {
                /*todo
                    message email ou speudo deja utiliser / traduction
                */
                Output::createAlert("Speudo ou email déjà utiliser", 'danger', 'index.php?view=View/User/Singup');
                die;
            }


        }

    }
    public function verifContain(array $contain):bool{
        foreach ($contain as $key => $item){
            if(is_string($item)){
                if(str_contains($item,'<')) return true;
            }

        }
        return false;
    }

    public function updateUser()
    {
        if(empty($_SESSION)) Output::createAlert('Erreur de connection','danger');
        $flag=false;

        if (is_string($_POST['login']) and is_string($_POST['password']) and is_string($_POST['email']) and
            is_string($_POST['FirstName']) and is_string($_POST['LastName']) and is_string($_POST['Adresse']) and is_string($_POST['tel'])) {
            $flag=true;
        }

        if($this->verifContain($_POST)) Output::createAlert('erreur contenue non autoriser','danger','index.php?view=View/User/Singup');

        Acess::CheckUserSession($_SESSION['userid']);
        $update=false;
        $u = new \App\Model\user();
        $user=$u->GetUserByUserId($_SESSION['userid']);
        $sql = 'Update user set';
        if(!empty($_POST['password']) && $_POST['password']!=$user['userPassword']){
            $update=true;
            $sql.= " userPassword= '".password_hash($_POST['password'],PASSWORD_DEFAULT) ."',";
        }
        if(!empty($_POST['email']) && filter_input(INPUT_POST,'email',FILTER_VALIDATE_EMAIL) && $_POST['email']!=$user['userMail']){
            if(Acess::CheckUserMailOrLogin($_POST['email'])){
                $update=true;
                $sql.= " userMail='".$_POST['email']."',";
            }else{
                Output::createAlert("Email déjà utiliser","danger",'index.php?view=api/user/Profile/' . $user['idUser']);
            }
        }
        if(!empty($_POST['FirstName']) && $_POST['FirstName']!= $user['userFirstName']){
            $sql.= " userFirstName='".htmlentities($_POST['FirstName'])."',";
            $update=true;
        }
        if(!empty($_POST['LastName'])&&$_POST['LastName']!=$user['userLastName']){
            $sql.= " userLastName='".htmlentities($_POST['LastName'])."',";
            $update=true;
        }
        if(!empty($_POST['Adresse'])&&$_POST['Adresse']!=$user['adresse'] ){
            $sql.= " adresse='".htmlentities($_POST['Adresse'])."',";
            $update=true;
        }
        if(!empty($_POST['tel'])&&$_POST['tel']!=$user['Numtel']){
            $sql.= " NumTel='".htmlentities($_POST['tel'])."',";
            $update=true;
        }
        if(!empty($_POST['Birthday'])&&$_POST['Birthday']!=$user['birthday']){
            $sql.= " birthday='".htmlentities($_POST['Birthday'])."',";
            $update=true;
        }
        if(!empty($_POST['Nation'])&&$_POST['Nation']!=$user['nationaliter']){
            $sql.= " nationaliter='".htmlentities($_POST['Nation'])."',";
            $update=true;
        }
        if(!empty($_POST['lang'])  && $_POST['lang']!=$user['lang']){
            $sql.= " lang='".htmlentities($_POST['lang'])."',";
            $update=true;
        }
        if(!empty($_FILES['photo'])){
            $this->UploadImage($_SESSION['userid'],true);
        }
        if($update and $flag){
            $sql = substr($sql,0,-1);
            $sql .=" where idUser='".$_SESSION['userid']."'";
            $u->UpdateUser($sql);
            Output::createAlert("Update réussit","success",'index.php?view=api/user/Profile/' . $user['idUser']);
        }
    }

    public function Login(): void
    {
        if (!$_POST) {
            header('HTTP/1.1 405');
        }
        $flage = false;

        if((is_string($_POST['login'])) and is_string($_POST['password'])) $flage=true;

        if($this->verifContain($_POST)) Output::createAlert('erreur contenue non autoriser','danger','index.php?view=View/User/Singup');

        if ((!empty($_POST['login'])) && (!empty($_POST['password'])) && $flage) {
            $USER = new \App\Model\user();
            $user = $USER->GetUserByUserName(trim(htmlentities($_POST['login'])));

            if (empty($user[0])) {
                Output::createAlert("L'user n'existe pas", 'danger', 'index.php?view=View/User/Login');
                die();
            }

            if (password_verify($_POST['password'], $user['userPassword'])) {
                if(!Acess::isBan($user['idUser'])){

                 session_destroy();
                    session_name('Web' . date('Ymd'));
                    session_id(bin2hex(openssl_random_pseudo_bytes(32)));
                    session_start(['cookie_lifetime' => 3600]);
                    $USER->SetLastLogin($user['idUser']);
                    $_SESSION['userid'] = $user['idUser'];
                    $_SESSION['lang'] = $user['lang'];
                    $_SESSION['admin'] = Acess::isAdmin();

                    /*todo traduction*/
                    Output::createAlert("Bienvenue " . $user['userName'], 'success', 'index.php?view=api/user/Profile/' . $user['idUser']);

                }else Output::createAlert("Erreur votre compte : " . $user['userName']." à été bannie", 'danger', 'index.php?');

            }else{
                Output::createAlert("Mot de passe ou speudo incorrect", 'danger', 'index.php?view=View/User/Login');

            }
        }
    }

    public function logout()
    {
        session_unset();
        session_destroy();
        session_write_close();
        header('Location: index.php');
        die();
    }

    public function Profile(int $idUser): void
    {

        Acess::CheckUserSession($idUser);
        $USER = new \App\Model\user();
        $user = $USER->GetUserByUserId($idUser);
        $userProfile = $this->formatProfile($user, true);
        Output::render('Profile', $userProfile);
    }
    //formate data
    protected function formatProfile(array $user, bool $img = false): array
    {

        for ($i = 0; $i < 11; $i++) {
            unset($user[$i]);
        }

        if (!empty($user)) {
            $userFormat =new ArrayObject($user);
        }

        unset($userFormat['idUser']);
        if(array_key_exists("userPassword",(array)$userFormat)) unset($userFormat['userPassword']);
        else unset($userFormat['password']);


        $img = new \App\Model\user();
        if ($img) {
            $imgUrl = $img->GetImage($user['idUser']);
            $userFormat['Image'] = $imgUrl;
        }


        return (array) $userFormat;
    }


    //save picture in the database
    function UploadImage(int $userId, bool $Update=false): void
    {
        if (!empty($_FILES['photo']['tmp_name']) && !empty($_FILES['photo']['name']) &&
            is_uploaded_file($_FILES['photo']['tmp_name']) && in_array($_FILES['photo']['type'], self::$image)) {
            $photoPath = 'image' . DIRECTORY_SEPARATOR . 'user' . DIRECTORY_SEPARATOR . 'photo' . DIRECTORY_SEPARATOR;
            $imgPath = getcwd() . DIRECTORY_SEPARATOR . $photoPath;


            if (!is_dir($imgPath)) {
                mkdir($imgPath, 0755, true);
            }

            $extension = pathinfo($_FILES['photo']['name'], PATHINFO_EXTENSION);
            $des = $photoPath . $userId . '.' . $extension;
            $url = $photoPath . $userId . '.' . $extension;
            $moveDef = move_uploaded_file($_FILES['photo']['tmp_name'], $des);
            if($Update){
                $user = new \App\Model\user();
                $user->UpdateImage($url, $userId);
            }else{
                if ($moveDef) {
                    $user = new \App\Model\user();
                    $user->insertImage($url, $userId);
                } else {
                    /*todo
                        message erreur
                    */
                    throw  new Exception();
                }
            }


        }
    }

    function exportImage(int $idUser)
    {
        $user = new \App\Model\user();
        $USER = $user->GetUserByUserId($idUser);
        $usrProfile = $this->formatProfile($USER);
        Output::render('exportImage', $usrProfile);
    }

    function exportUser(int $idUser,string $format)
    {
        $user = new \App\Model\user();
        $Use = $user->GetUserByUserId($idUser);
        $userFormat = $this->formatProfile($Use);
        $userFormat['Format'] = $format;
        Output::render('exportUser', $userFormat);
    }

    function exportAllFormationUser($txt,$forma){
        $COUR=new \App\Model\cour();
        $Forma=$COUR->GetFormationByLike($txt);
        if((!empty($Forma))){
            $Forma['Format'] = $forma;
            $Forma['userName']='formation';
            $size = count($Forma);
            for($i=0;$i<$size;$i++) unset($Forma[$i]['0']);
            Output::render('exportFormation', $Forma);
        } else
            Output::render('messageBox','Erreur formation non trouver');
    }

    function DeleteUser(int $idUser){
        $user = new \App\Model\user();
        $user->DeleteUser($idUser);
        header('Location: index.php?view=api/Bootstrap/Admin/ListUser/n/n');
    }
    function UpdateRoleOrFormationForUser(int $idUser,string $add){
        $COUR= new \App\Model\cour();
        $ROLE = new Role();

        if($add=='addRole'){
               $idRole = $ROLE->GetIDRole($_POST['modal']);
                $ROLE->GiveRoleByIdUser($idUser,$idRole);
        }elseif ($add=='addFormation'){
            $idFoma= $COUR->GetIdFromation($_POST['modal']);
            $COUR->VerifBeforInscription($idUser,$idFoma);
        }elseif ($add=='deleteRole'){
            $idRole = $ROLE->GetIDRole($_POST['modal']);
            $ROLE->RemoveRole($idUser,$idRole);
        }elseif ($add=='deleteFormation'){
            $idFoma= $COUR->GetIdFromation($_POST['modal']);
            $COUR->ExcluFormation($idUser,$idFoma);
        }
        header('Location: index.php?view=api/Bootstrap/AdminUpdateUSer/'.$idUser);
    }

}