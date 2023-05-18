<?php

namespace App\Model;
use App\Helpers\DB;

class user{

    public function  CreateUser(Array $user): bool{
        $db = DB::conect();
        $exec=$db->prepare('Insert into user(userName,userPassword,userMail,userFirstName,userLastName,nationaliter,birthday,adresse,NumTel,lang,DateInscri)values (?,?,?,?,?,?,?,?,?,?,now())');
        $exec->execute([$user['login'],$user['password'],$user['email'],$user['FirstName'],$user['LastName'],$user['Nation'],$user['Birthday'],$user['Adresse'],$user['NumTel'],$user['lang']]);
        if($exec->fetchColumn())return true;
        return false;
 }
    public function DeleteUser($idUSer){
        $db = DB::conect();
        $exec=$db->prepare('Delete from user where idUser=?');
        $exec->execute([$idUSer]);
    }

    public function GetUserByUserName(String $UserName):?array{
     $db = DB::conect();
     $data=[];
     $exec=$db->prepare('select 
        idUser,
        userName,
        userPassword,
        userMail,
        userFirstName,
        userLastName,
        nationaliter,
        birthday,
        adresse,
        Numtel,
        lang
        from user where userName= ?;');
     $exec->execute([$UserName]);
     while ($data_tmp = $exec->fetchAll()){
         $data[]=$data_tmp;
     }
     if(!empty($data)) return $data[0][0];
     else return array(null);
 }
    public  function GetUserByUserId(int $idUser):? Array {
        $db = DB::conect();
        $data=[];
        $exec=$db->prepare('select 
        idUser,
        userName,
         userPassword,
        userMail,
        userFirstName,
        userLastName,
        nationaliter,
        birthday,
        adresse,
        Numtel,
        lang
        from user where idUser= ?;');
        $exec->execute([$idUser]);
        while ($data_tmp = $exec->fetchAll()){
            $data[]=$data_tmp;
        }
        if(!empty($data)) return $data[0][0];
        else return null;
    }

    public  function GetUserNameByUserId(int $idUser):?array {
        $db = DB::conect();
        $data=[];
        $exec=$db->prepare('select 
        userName
        from user where idUser= ?;');
        $exec->execute([$idUser]);
        while ($data_tmp = $exec->fetchAll()){
            $data[]=$data_tmp;
        }
        if(!empty($data)) return $data[0];
        else return null;
    }


    public function GetAllDataUserForAdmin():?Array{
     $db = DB::conect();
     $data=[];
     $exec=$db->prepare('select
        idUser,
        userName,
        userMail,
        userFirstName,
        userLastName,
        nationaliter,
        birthday,
        adresse,
        Numtel,
        lang,
        DateInscri
     from user ; ');
  $exec->execute();
     while ($data_tmp = $exec->fetchAll()){
         $data[]=$data_tmp;
     }
        if(!empty($data)) return $data[0];
        else return null;
 }

    public function GetAllUserName(): ?array{
     $db = DB::conect();
     $data =[];
     $exec=$db->prepare('select userName from user ');
  $exec->execute();
     while ($data_tmp = $exec->fetchAll()){
         $data[]=$data_tmp;
     }
        if(!empty($data)) return $data[0];
        else return null;
 }

public function GetUserID(String $userName):int{
     $db = DB::conect();
     $data=[];
     $exe = $db->prepare('select idUser from user where userName=?');
     $exe->execute([$userName]);
     while ($data_tmp = $exe->fetchAll()){
         $data[]=$data_tmp;
     }
     return intval($data[0][0]['idUser']);
 }

    public function VerifUserNameOrMail(String $user,String $mail) : bool{
     $db =DB::conect();
     $exe = $db->prepare('select idUser from user where userName=? or userMail=?');
     $exe->execute([$user,$mail]);
     if($exe->fetchColumn()) return false ;
     return true;
 }

    public function insertImage(String $url, int $idUser):bool{
    $db = DB::conect();
    $exe = $db->prepare('insert into image(url) values (?)');
    $exe->execute([$url]);
    $idImage = $db->lastInsertId();
    $exe = $db->prepare('insert into user_image(idIMG,idUser) values (?,?)');
    $exe->execute([$idImage,$idUser]);
     if($exe->fetchColumn()) return false ;
     return true;
 }

    public function GetImage(int $idUser){
     $db = DB::conect();
     $exe= $db->prepare('select idIMG from user_image where idUser=?');
     $exe->execute([$idUser]);
     $data_tmp = $exe->fetchAll();
     if(!empty($data_tmp)){
         $idImg= $data_tmp[0]['idIMG'];
         $exec= $db->prepare('select url from image where id=?');
         $exec->execute([$idImg]);
         $data_tmp2 = $exec->fetchAll();
         return $data_tmp2[0]['url'];
     }
     return null;
    }


    public function UpdateUser(String $sql):bool{
        if($this->CheckString($sql)){
            $db =DB::conect();
            $db->query($sql);
            return true;
        }
        return false;
    }
    public function UpdateImage(String $url,int $idUser):bool{
        if($this->CheckString($url)){
            $db = DB::conect();
            $exe= $db->prepare('select idIMG from user_image where idUser=?');
            $exe->execute([$idUser]);
            $data_tmp = $exe->fetchAll();
            if(!empty($data_tmp)){
                $idImg= $data_tmp[0]['idIMG'];
                $exec= $db->prepare('Update image set url=?  where id=?');
                $exec->execute([$url,$idImg]);
            }else{
                $this->insertImage($url,$idUser);
            }
            return true;
        }
            return false;
    }
    private function CheckString(String $txt): bool{
        $Check=strtolower($txt);
        if(str_contains($Check, 'delete')) return false;
        elseif(str_contains($Check,'drop')) return false;
        elseif(str_contains($Check,'truncate')) return false;
        elseif(str_contains($Check,'rename')) return false;
        return true;
    }
    public function SetLastLogin(int $idUser){
        $db = DB::conect();
        $exe= $db->prepare('select id from lastlogin where idUser=?');
        $exe->execute([$idUser]);
       if($exe->fetchColumn()){
            $exes = $db->prepare('Update lastlogin set date=date_format(now(),"%Y-%c-%d %H:%i:%s") where idUser=?');
            $exes->execute([$idUser]);
        }else{
            $exes = $db->prepare('insert into lastlogin (idUser,date) values (?,date_format(now(),"%Y-%c-%d %H:%i:%s"))');
            $exes->execute([$idUser]);
        }
    }
    public function GetLAstLogin(int $idUser):string{
        $db = DB::conect();
        $exe= $db->prepare('select date from lastlogin where idUser=?');
        $exe->execute([$idUser]);
        while ($data_tmp = $exe->fetchAll()){
            $data[]=$data_tmp;
        }
        return $data[0][0]['date'];

    }
    public function  GetAllTeacher():?array{
        $db = DB::conect();
        $data=[];
        $exe= $db->prepare('select userFirstName from user inner join user_role where user.idUser=user_role.idUser and user_role.idRole=2');
        $exe->execute();
        while ($data_tmp = $exe->fetchAll()){
            $data[]=$data_tmp;
        }
        if(!empty($data))return $data[0];
        return null;
    }
}