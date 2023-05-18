<?php

namespace App\Helpers;

use App\Model\cour;
use App\Model\Role;
use App\Model\user;
use mysql_xdevapi\Exception;

class Bootstrap
{
    public static function messageBox(string $message, string $class = 'danger'): string
    {
        return '<div class="alert alert-' . $class . '">' . $message . '</div>';
    }


    public static function Profile(array $data): string
    {
        $body = '';

        foreach ($data as $key => $value) {
            if ($key == 'Image') {
                $body .= '<div class="row">
                            <div class="col-6">
                                <img src="' . $value . '"  class="d-block img-fluid" id="profile-photo">
                                <br><a href="Api/Route/user/exportImage/' . $_SESSION['userid'] . '"  class="btn btn-sm btn-primary">Exporter l\'image</a>
                            </div>
                          </div>';
            } else $body .= '<tr><th>' . $key . '</th><td>' . $value . '</td></tr>';
        }
        return '<div id="accordion">
                 <div class="card">
                    <div class="card-header" id="headingOne">
                      <h5 class="mb-0">
                          <h3>Profile</h3>          
                      </h5>
                    </div>
                   
                       <div class="card-body">' .
            '<table class="table">' . $body . '</table>'
            . '<p> <a  href="Api/Route/user/exportUser/' . $_SESSION['userid'] . '/json"  class="btn btn-sm btn-primary">Exporte en JSon</a> </p>'
            . '<p><a href="Api/Route/user/exportUser/' . $_SESSION['userid'] . '/csv"  class="btn btn-sm btn-primary">Exporte en CSV</a></p>'
            .'</div>'.
            self::UpdateProfile() .
            '</div>';
    }
    //view of the update for profile
    public static function UpdateProfile(): string
    {
        return '
                  <div class="card">
                    <div class="card-header" id="headingOne">
                      <h5 class="mb-0">
                       
                         <h3>Update</h3>
                     
                      </h5>
                    </div>

             <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#Update" aria-expanded="false" aria-controls="collapseTwo"></button>
             
              <div class="card-body">
                    <form action="index.php?view=api/user/updateUser" method="post" enctype="multipart/form-data">
                    <label for="passwordUp">Nouveaux Mot de passe</label>
                    <input type="password" id="passwordUp" name="password" class="form-control" >
                    <label for="emailUp">Email</label>
                    <input type="email" id="emailUp" name="email" class="form-control" >
                    <label for="FirstNameUp">Prénom</label>
                    <input type="text" id="FirstNameUp" name="FirstName" class="form-control" >
                    <label for="LastNameUp">Nom</label>
                    <input type="text" id="LastNameUp" name="LastName" class="form-control" >
                    <label for="AdresseUp">Adresse</label>
                    <input type="text" id="AdresseUp" name="Adresse" class="form-control" >
                    <label for="TelUp">Numéro de téléphone</label>
                    <input type="tel" id="TelUp" name="tel" class="form-control" >
                    <label for="BirthdayUp">Date de naissance</label>
                    <input type="date" id="BirthdayUp" name="Birthday" class="form-control" >
                    <label for="NationUp">Pays</label>
                    <select name ="Nation" id="NationUp" class="form-control" >
                        <option value="BEL">Belgique</option>
                        <option value="FRA">France</option>
                    </select>
                    <label for="langUp">Language</label>
                    <select name="lang" id="langUp" class="form-control" >
                        <option value="en" selected>English</option>
                        <option value="fr">Français</option>
                    </select>
                    <label for="photoUp">Photo</label>
                    <p><input type="file" id="photoUp" name="photo"></p>
                    <input type="submit" class="btn btn-primary" value="Valider">
                    </form>
            </div>
          </div>
            </div>

';
    }

    public static function exportImage(array $data): void
    {

        $path = ROOT_PATH . DIRECTORY_SEPARATOR . $data['Image'];
        $ext = explode('.', $data['Image']);
        header('Content-type: image');
        header('Content-disposition: attachment; filename="' . $data['userName'] . '.' . $ext[1] . '"');
        echo file_get_contents($path);



    }

    public static function exportUser(array $data): void
    {
        $filename = $data['userName'] . '_' . time() . '.' . $data['Format'];

        if ($data['Format'] == 'json') {
                header('Content-type: application/json');
                header('Content-disposition: attachment; filename="' . $filename . '"');

            unset($data['Format']);
           echo json_encode($data);

        } elseif ($data['Format'] == 'csv') {
            header('Content-type: test/csv');
            header('Content-disposition: attachment; filename="' . $filename . '"');
            unset($data['Format']);
            foreach ($data as $key => $value) {
                echo $key . ' : ' . $value . "\n";
            }
        }
    }
    public static function exportFormation(array $data): void
    {
        $filename = $data['userName'] . '_' . time() . '.' . $data['Format'];

        unset($data['userName']);

        if ($data['Format'] == 'json') {
            header('Content-type: application/json');
            header('Content-disposition: attachment; filename="' . $filename . '"');

            unset($data['Format']);
            echo json_encode($data);

        } elseif ($data['Format'] == 'csv') {
            header('Content-type: test/csv');
            header('Content-disposition: attachment; filename="' . $filename . '"');
            unset($data['Format']);
            foreach ($data as $key => $value) {
                foreach ($value as $Key=>$item) echo $Key . ' : ' . $item . "\n";
            }
        }
    }
    //tableaux liste des formations
    public static function tableCour(array $header,array $data,String $title): string{
        $Filtering='<p><input class="form-control" id="FilterDataTable" type="text" placeholder="Nom de la formation" > </p>';

        $Table ='<table class="table">';
        //----------head------------------------
        $Thead='<thead><tr>';

        foreach ($header[0] as $key){
            $Thead.= '<th scope="col">'.$key.'</th>';
        }
        $Thead.='</tr></thead>';
        //----------body------------------------
        $Tbody='<tbody id="dataBody">';
        $size = count($data);
        for($i=0;$i<$size;$i++){
            $Tbody.='<tr>';
            $count = (count($data[$i]))/2;
            for($x=0;$x<$count;$x++){
                unset($data[$i][$x]);
            }

            $idFor=$data[$i]['idForma'];
            unset($data[$i]['idForma']);
            unset($data[$i]['statue']);
            foreach ($data[$i] as $key ){

                $Tbody.='<th scope="row">'.$key.'</th>';
            }
            //-----------Button---------------------
            $COUR = new cour();
            if(!empty($_SESSION['userid']))$idUser =$_SESSION['userid'];
            else Output::createAlert('Erreur de connection','danger');
            if($COUR->CheckDemande($idUser,$idFor)){
                $Tbody.='<th scope="row"> En Attende de confirmation </th>';
            }elseif ($COUR->CheckDemandeRefu($idUser,$idFor)){
                $Tbody.='<th scope="row"> Demande refuser </th>';
            } elseif (($COUR->CheckInscrit($idUser,$idFor))){
                $Tbody.='<th scope="row"> Déjà inscrit</th>';
            }else{
                $Tbody.='<th scope="row"> <a href="index.php?view=api/cour/InsertDemande/'.$idUser.'/'.$idFor.'" class="btn btn-sm btn-success">+</a> </th>';
            }
            $Tbody.='</tr>';
        }

        //----------assemble------------------------
        $Tbody .='</tbody>';
        $Table.=$Thead.$Tbody.'</table>';

        return '<p>'. $Filtering .'</p>'.$Table .
            '<p> <a  id="exportJson"   class="btn btn-sm btn-primary">Exporte en JSon</a> </p>'
            . '<p><a  id="exportCSV" class="btn btn-sm btn-primary">Exporte en CSV</a></p>
';
        /*todo faire les exporte*/
    }

    public static function Admin(string $data,string $action,string $CourForma):string{
       return '
  <div class="row">
    <div class="col-sm-2">
        <div class="card">
            <div class="card-body"> 
                <div class="nav flex-column nav-pills"  role="tablist" aria-orientation="vertical">
                  <a class="nav-link "  data-toggle="pill" href="index.php?view=api/Bootstrap/Admin/ListUser/n/n" role="tab"  aria-selected="true">Liste d\'utilisateur</a>
                  <a class="nav-link"  data-toggle="pill" href="index.php?view=api/Bootstrap/Admin/Demande/n/n" role="tab"  aria-selected="false">Demande d\'incription</a>
                  <dl>
                  <dt><a class="nav-link "> Cour</a></dt>
                  <dd>  <a class="nav-link" data-toggle="pill" role="tab"  aria-selected="false" href="index.php?view=api/Bootstrap/Admin/CreatCour/add/n" >- Crée</a></dd>
                  <dd><a class="nav-link " data-toggle="pill" role="tab"  aria-selected="false"  href="index.php?view=api/Bootstrap/Admin/CreatCour/del/n">- Supprime </a></dd>
                  <dd><a class="nav-link " data-toggle="pill"  role="tab"  aria-selected="false" href="index.php?view=api/Bootstrap/Admin/CreatCour/up/n">- Modifier</dd>
                  <dt><a class="nav-link " data-toggle="pill"  role="tab"  aria-selected="false"> Formation</a></dt>
                  <dd>  <a class="nav-link " data-toggle="pill" role="tab"  aria-selected="false" href="index.php?view=api/Bootstrap/Admin/CreatFroma/add/n" >- Crée</a></dd>
                  <dd><a class="nav-link " data-toggle="pill"  role="tab"  aria-selected="false" href="index.php?view=api/Bootstrap/Admin/CreatFroma/del/n">- Supprime </a></dd>
                  <dd><a class="nav-link " data-toggle="pill"  role="tab"  aria-selected="false" href="index.php?view=api/Bootstrap/Admin/CreatFroma/up/n">- Modifier</dd>
                  </dl>
                  <a></a>
                </div>
            </div>
           </div>
          </div>  
    <div class="col-sm-10">
      <div class="card">
        <div class="card-body">
                    '. self::CartAdmin($data,$action,$CourForma) .  '
        </div>
      </div>
    </div>
</div>  
       ';
    }

    public static function CartAdmin(string $data,string $action,string $CourForma):string{

        if($data=='ListUser'){
           return self::ListUser();
        }elseif ( $data=='CreatFroma'){
            //-------Create
            if($action=='add')return self::createFormation();
            //-------Update
            elseif ($action=='up')return self::UpdateFormation(intval($CourForma));
            //-------Delete
            elseif ($action=='del')return self::DeleteFormation();

        }elseif ($data=='CreatCour'){
            //-------Create
            if($action=='add')return self::CreatCour();
            //-------Update
            elseif ($action=='up')return self::UpdateCour($CourForma);
            //-------Delete
            elseif ($action=='del')return self::DeleteCour();
        }elseif ($data == 'Demande'){
           return self::Demande();
        }
        return '';
    }

    private static function Demande():string{
        $FORMA= new cour();
        $AllDemande = $FORMA->GetAllDemandeAttendUserFormation();
        $Head= array("Nom d'utilisateur","Formation","Valide","Refuser");
        $Button=array(["success"=>"V","index.php?view=api/Cour/ValidationDemande/Val"],["danger"=>"X","index.php?view=api/Cour/ValidationDemande/Sup"]);
        $size=0;
        if(!empty($AllDemande)) $size = count($AllDemande[0])/2;
        $Mod='<h1>Demande en attende</h1>';

        $Mod.=self::table($AllDemande,$size,$Head,$Button);

        return $Mod;
    }

    private static function table(?array $DataTable,int $size,array $DataHeader,array $Button=null){

        $Mod='<table class="table"> <thead><tr>';
        //---------------head----------------
        foreach ($DataHeader as $item){
            $Mod.='<th scope="col">'.$item.'</th>';
        }
        //--------------Body-----------------
        $Mod.='</tr></thead><tbody>';

        if(!empty($DataTable)){

        foreach ($DataTable as $Key => $value){
            $Mod.='<tr>';
            for($i=0;$i<$size;$i++) $Mod.='<th scope="row">'.$value[$i].'</th>';

            if(!empty($Button)){

                foreach ($Button as $key=>$item){
                    foreach ($item as $Value=>$val){
                    if($Value=='success'){
                        $Mod.='<th scope="row"><a href="'.$item[0].'/'.$value[0].'/'.$value[1].'" class="btn btn-sm btn-success">'.$val.'</a></th>';
                    }elseif ($Value=='danger'){
                        $Mod.='<th scope="row"><a href="'.$item[0].'/'.$value[0].'/'.$value[1].'" class="btn btn-sm btn-danger">'.$val.'</a></th>';
                    }

                }
            }
            }
        }

            $Mod.='</tr>';
        }
        $Mod.='</tbody></table>';
        return $Mod;
    }

    private static function ListUser():string{
        $USER = new user();
        $ROLE = new Role();
        $FORMA= new cour();
        $dataUser = $USER->GetAllDataUserForAdmin();
        $tHeader=   array(["id","Speudo","Email","Prénom","Nom","Nationaliter","Date de naissance","Adresse","Numéro de telephone","Langue","Date d'inscription","Role","Formation","Dernier connection","Supprimer"]);


        $Table='<table class="table">';

        $Thead='<thead><tr>';
        foreach ($tHeader[0] as $key){
            $Thead.= '<th scope="col">'.$key.'</th>';
        }
        $Thead.='</tr></thead>';

        $Table.= $Thead;

        if(!empty($dataUser)){
            $Table.='<tbody id="dataBody">';
            foreach ($dataUser as $key){

                $userRole = $ROLE->GetRoleUser($key['idUser']);
                $userFomation = $FORMA->GetAllFromationUser($key['idUser']);
                $userLastCo = $USER->GetLAstLogin($key['idUser']);

                $size = count($key)/2;
                if(!empty($sizeFoma))$sizeFoma= count($userFomation)/2;
                else $sizeFoma=0;


                for($i=0;$i<$size;$i++)unset($key[$i]);
                for($i=0;$i<$sizeFoma;$i++)unset($userRole[$i]);

                $Table.='<tr>';
                foreach ($key as $Key2){
                    if($Key2==$key['idUser'])$Table.='<th scope="row"> <a href="index.php?view=api/Bootstrap/AdminUpdateUSer/'.$Key2.'"> '.$Key2.'</a></th>';
                    else $Table.='<th scope="row">'.$Key2.'</th>';
                }
                $i=0;
                $Table.='<th scope="row">';
                foreach ($userRole as $Key2){
                    $Table.=$Key2['NameRole'];
                    $i++;
                    if($i%1==0)$Table.='</br>';
                }
                $Table.='</th>';
                if(!empty($userFomation)){

                    $Table.='<th scope="row">';
                    $Data='';
                    $i=0;
                    foreach ($userFomation as $Key2){
                        $Data.=$Key2['nameForma'].',';
                        $i++;
                        if($i%2==0){$Data.='</br>';};

                    }
                    $Table.=$Data.'</th>';
                }else $Table.='<th scope="row">Inscrit à aucune formations</th>';

                if($userLastCo!='') $Table.='<th scope="row">'.$userLastCo.'</th>';
                else $Table.='<th scope="row">L\'utilisateur ne ses jamais connecter</th>';

                $Table.='<th scope="row"> <a href="index.php?view=api/Modal/RemoveUserModal/'.$key['idUser'].'" class="btn btn-sm btn-danger">X</a> </th>';

                $Table.='</tr>';

            }
            $Table.='</tbody> </table>';
        }

        return $Table ;
    }

    static function UpdateUser(int $idUser):string{
        $ROLE = new Role();
        $COUR = new cour();
        //-------------RoleUserFilter-------------------
        $userRole = $ROLE->GetRoleUser($idUser);
        $AllRole = $ROLE->GetAllRole();

         $i=0;
        foreach ($AllRole as $Kay => $value){
            foreach($userRole as $Key=>$values){
                if($value['NameRole']==$values['NameRole'])unset($AllRole[$i]);
            }
            $i++;
        }
        //-------------FormationUserFilter---------------
        $AllFormation=$COUR->GetAllNameFromation();
        $FormationUser= $COUR->GetAllFromationUser($idUser);
        if(is_array($FormationUser)) {
            $i = 0;
            foreach ($AllFormation as $Kay => $value) {
                foreach ($FormationUser as $Key => $values) {
                    if ($value['nameForma'] == $values['nameForma']) {
                        unset($AllFormation[$i]);
                    }
                    $i++;
                }
            }
        }
        $Mod ='<p>'. self::ModalComboTable($AllRole,$userRole,$idUser,"deleteRole","addRole"). '</p>';
        $Mod .='<p>'. self::ModalComboTable($AllFormation,$FormationUser,$idUser,"deleteFormation","addFormation"). '</p>';
        $Mod.=' <a href="index.php?view=api/Bootstrap/Admin/ListUser/n/n" class="btn btn-sm btn-danger">Fermer</a>';
        return $Mod;
    }

    //modal for delete
    static function ModalPopUp(String $modalTitle,int $id=null,String $GoOn,String $GoBack):string{
        $Mod='
<div class="row">
        <div class="col-sm-3">
        </div>
         <div class="col-sm-5">
        <div class="card">
          <div class="card-body">
              <p>voulez-vous vraiment supprimer '.$modalTitle.'</p>  
             <p>Si oui veuillez taper le mot "Supprimer" </p>
             <p><input class="form-control" id="FilterDataTable" type="text" placeholder="..." required>
                <a href="'.$GoOn.$id.'" class="btn btn-sm btn-success">Supprimer</a>
                <a href="'.$GoBack.'" class="btn btn-sm btn-danger">Fermer</a>
             </p>
          </div>
          </div>
        </div>
      </div>  
        ';

      return $Mod;
    }
    //Modal with combobox
    private static function ModalComboTable(array $DataCombobox,array|string $DataTableBody, int $idUser,string $actionDel,String $actionAdd){
        $Mod= '
      <div class="col-sm-3">
      </div>
        <div class="col-sm-5">
            <div class="card">
                <div class="card-body">
                    <form action="index.php?view=api/user/UpdateRoleOrFormationForUser/'.$idUser.'/'.$actionAdd.'" method="post" enctype="multipart/form-data">
                        <select name="modal" id="modal" class="form-control" required>';

        //-------------------ComboBox---------------------------------------------
                    foreach ($DataCombobox as $Key=>$Value){

                        $Mod.= '<option value="'.$Value['0'].'">' .$Value['0']. '</option>';
                    }
            $Mod.='</select> <input type="submit" class="btn btn-primary" value="Valider"> </form>  ';
            $Mod.='<table>';
            $Mod.='<thead><tr>';
            $Mod.= '<th scope="col"> </th><th scope="col">   </th>';
        $Mod.='</tr></thead>';

        //-------------------Data Table---------------------------------------------

            if(is_array($DataTableBody)){

                    foreach ($DataTableBody as $Key=>$value){

                        $Mod.='<tr>';
                        $Mod.='<th scope="row"><form action="index.php?view=api/user/UpdateRoleOrFormationForUser/'.$idUser.'/'.$actionDel.'" method="post" enctype="multipart/form-data">
                             <input id="modal" name="modal" value="'.$value['0'].'" readonly> 
                            <input type="submit"  value="X"></form></th>';
                        $Mod.='</tr>';
                    }
            }else{
                $Mod.='<tr>';
                $Mod.='<th scope="row">'.$DataTableBody.'</th>';
                if(!empty($DataTableBody)) $Mod.='<th scope="row"><form action="index.php?view=api/user/UpdateRoleOrFormationForUser/'.$idUser.'/'.$actionDel.'" method="post" enctype="multipart/form-data">
                             <input id="modal" name="modal" value="'.$DataTableBody.'" readonly> 
                            <input type="submit"  value="X"></form></th>';
                $Mod.='</tr>';
            }
             $Mod.= '</table> 
                   
                </div>
            </div>
        </div>
         
 ';
        return $Mod;
    }
    private static function CreatCour():string{
        $USER = new user();
        $AllTeacher= $USER->GetAllTeacher();
        $Mod='<form action="index.php?view=api/cour/createCour" method="post" enctype="multipart/form-data">
            <label for="Nom">Nom de cour</label></br>
            <input type="text" id="Nom" name="Nom" required></br>
            <label for="prof">Professeur du cour</label>
        <select name="prof" id="prof" class="form-control" required>';
        if(!empty($AllTeacher)){
            foreach ($AllTeacher as $Key =>$value){
                $Mod.=  ' <option value="'.$value['userFirstName'].'" selected>'.$value['userFirstName'].'</option>';
            }
        }
        $Mod.='</select> ';
        $Mod.=' <input type="submit" class="btn btn-primary" value="Valider"></form>';
        return $Mod;
    }
    private static function UpdateCour(string $cour=''):string{
        $COUR= new cour();
        $USER = new user();
        $NameCour='';
        $modifCour='';

        if((!empty($cour))&&($cour!='n')){
            $modifCour=$COUR->GetCourByName($cour);
            $NameCour=$modifCour[0]['courName'];
        }
        $DataCombobox=$COUR->GetAllCour();

        //------------------------------------------------------------------------
        $Mod= '
      <div class="col-sm-3">
      </div>
        <div class="col-sm-5">
            <div class="card">
                <div class="card-body">
                    <form action="index.php?view=api/Bootstrap/UpdateCour" method="post" enctype="multipart/form-data">
                        <select name="modal" id="modal" class="form-control" required>';

        //-------------------ComboBox---------------------------------------------

        foreach ($DataCombobox as $Key=>$Value){
            if($NameCour==$Value['0']) $Mod.= '<option value="'.$Value['0'].'" selected>' .$Value['0']. '</option>';
            else $Mod.= '<option value="'.$Value['0'].'">' .$Value['0']. '</option>';
        }
        $Mod.='</select> <input type="submit" class="btn btn-primary" value="Valider"> </form> </br> ';
        //-------------------UpdateCour---------------------------------------------

        $AllTeacher= $USER->GetAllTeacher();

        $Mod.='<form action="index.php?view=api/cour/UpdateCour/'.$cour.'" method="post" enctype="multipart/form-data">
            <label for="Nom">Nom de cour</label></br>
            <input type="text" id="Nom" name="Nom" value="'.$NameCour.'" placeholder="'.$NameCour.'" ></br>
            <label for="prof">Professeur du cour</label>
            <select name="prof" id="prof" class="form-control" >';
             if(empty($modifCour[0]['teacher'])) $Mod.='<option value="'.null.'" selected>Aucun professeur attribuer</option>';

        if(!empty($AllTeacher)){
            foreach ($AllTeacher as $Key =>$value){
                if((!empty($modifCour))&&($modifCour[0]['teacher']==$value['userFirstName']))$Mod.=  ' <option value="'.$value['userFirstName'].'" selected>'.$value['userFirstName'].'</option>';
                else $Mod.=  ' <option value="'.$value['userFirstName'].'" >'.$value['userFirstName'].'</option>';
            }
        }
        $Mod.='</select> ';
        $Mod.='<select name="statut" id="statut" class="form-control" >';
        $Mod.='<option value="1"';
            if((!empty($modifCour))&&($modifCour[0]['statut']=='1')){$Mod.='selected';}
            $Mod.= '>Actif</option>';
        $Mod.='<option value="0"';
            if((!empty($modifCour))&&($modifCour[0]['statut']=='0')){$Mod.='selected';}
        $Mod.='>InActif</option>';
        $Mod.='</select>';
        $Mod.=' <input type="submit" class="btn btn-primary" value="Valider"></form></div></div></div>';

        return $Mod;
    }
    private static function DeleteCour(){
        $COUR= new cour();
        $DataCombobox=$COUR->GetAllCour();
        //------------------------------------------------------------------------
        $Mod= '
      <div class="col-sm-3">
      </div>
        <div class="col-sm-5">
            <div class="card">
                <div class="card-body">
                    <form action="index.php?view=api/Modal/RemoveCourModal" method="post" enctype="multipart/form-data">
                        <select name="modal" id="modal" class="form-control" required>';

        //-------------------ComboBox---------------------------------------------

        foreach ($DataCombobox as $Key=>$Value){
            $Mod.= '<option value="'.$Value['idCour'].'">' .$Value['courName']. '</option>';
        }
        $Mod.='</select> <input type="submit" class="btn btn-primary" value="Valider"> </form> </br> 
            </div></div></div>';

        return $Mod;
    }

    public static function createFormation(): string
    {
        $COUR = new cour();
        $AllCour = $COUR->GetAllCour();
        $Mod='<form action="index.php?view=api/cour/createFormation" method="post" enctype="multipart/form-data">
            <label for="Nom">Nom de la formation</label></br>
            <input type="text" id="Nom" name="Nom" required></br>
            <label for="LVEtude">Niveaux d\'etude</label>
            <select name="LVEtude" id="LVEtude" class="form-control" >
                <option value="Master">Master</option>
                <option value="Bac">Bac</option>
                <option value="BES">BES</option>
                <option value="DS">DS</option>
                <option value="DI">DI</option>
            </select>
            <label for="dateStart">Date de debut de la formation</label>
            <input type="date" id="dateStart" name="dateStart" class="form-control" required> 
            <label for="dateEnd">Date de fin de la formation</label>
            <input type="date" id="dateEnd" name="dateEnd" class="form-control" required>';
        $i=0;
        $count=0;
        foreach ($AllCour as $value){
            $Mod.='<input type="checkbox" name="'.$count.'" value="'.$value['idCour'].'">
                <label for="'.$count++.'">'.$value['courName'].'</label>
            ';
            $i++;
            if($i%3==0)$Mod.='</br>';
        }
        $Mod.='</br> <input type="submit" class="btn btn-primary" value="Valider"></form>';
        return $Mod;
    }

    public static function UpdateFormation(int $idForma=null){
        $COUR= new cour();
        $ModifFormation='';
        $NameForma='';
        $FormatEtude='';
        $dateStart='';
        $dateEnd='';
        $idAllCour='';

        if((!empty($idForma))&&($idForma!='0')){
            $ModifFormation= $COUR->GetFromationByid($idForma);
            $NameForma=$ModifFormation[0]['nameForma'];
            $FormatEtude=$ModifFormation[0]['LVEtude'];
            $dateStart=date($ModifFormation[0]['dateStart']);
            $dateEnd=date($ModifFormation[0]['dateEnd']);
            $idAllCour=$COUR->GetAllCourIdFormation($idForma);
        }
        $AllCour=$COUR->GetAllCour();
        $DataCombobox=$COUR->GetAllNameFromation();

        //------------------------------------------------------------------------
        $Mod= '
      <div class="col-sm-3">
      </div>
        <div class="col-sm-5">
            <div class="card">
                <div class="card-body">
                    <form action="index.php?view=api/Bootstrap/UpdateForma" method="post" enctype="multipart/form-data">
                        <select name="modal" id="modal" class="form-control" required>';

        //-------------------ComboBox---------------------------------------------

        foreach ($DataCombobox as $Key=>$Value){

            if($NameForma==$Value['0']) $Mod.= '<option value="'.$Value['0'].'" selected>' .$Value['0']. '</option>';
            else $Mod.= '<option value="'.$Value['0'].'">' .$Value['0']. '</option>';
        }
        $Mod.='</select> <input type="submit" class="btn btn-primary" value="Valider"> </form> </br> ';
        //----------------------------------Data-----------------------------------
        $Mod.='<form action="index.php?view=api/cour/UpdateFormation/'.$idForma.'" method="post" enctype="multipart/form-data">
            <label for="Nom">Nom de la formation</label></br>
            <input type="text" id="Nom" name="Nom" placeholder="'.$NameForma.'" ></br>
            <label for="LVEtude">Niveaux d\'etude</label>
            <select name="LVEtude" id="LVEtude" class="form-control" >
                <option value="Master"'.self::CheckString($FormatEtude,"Master").'>Master</option>
                <option value="Bac" '.self::CheckString($FormatEtude,"Bac").'>Bac</option>
                <option value="BES" '.self::CheckString($FormatEtude,"BES").'>BES</option>
                <option value="DS" '.self::CheckString($FormatEtude,"DS").'>DS</option>
                <option value="DI" '.self::CheckString($FormatEtude,"DI").'>DI</option>
            </select>
            <label for="dateStart">Date de debut de la formation</label>
            <input type="date" id="dateStart" name="dateStart" class="form-control" value="'. $dateStart.'" > 
            <label for="dateEnd">Date de fin de la formation</label>
            <input type="date" id="dateEnd" name="dateEnd" class="form-control" value="'.$dateEnd.'" >';

        $Mod.='<select name="statut" id="statut" class="form-control" >';
        $Mod.='<option value="1"';
        if((!empty($ModifFormation))&&($ModifFormation[0]['statue']=='1')){$Mod.='selected';}
        $Mod.= '>Actif</option>';
        $Mod.='<option value="0"';
        if((!empty($ModifFormation))&&($ModifFormation[0]['statue']=='0')){$Mod.='selected';}
        $Mod.='>InActif</option>';
        $Mod.='</select>';

        $i=0;
        $count=0;
        foreach ($AllCour as $value){

            $Mod.='<input type="checkbox" name="'.$count.'" value="'.$value['idCour']  .'" '.self::CheckCombo($idAllCour,$value['idCour']).'>
                <label for="'.$count++.'">'.$value['courName'].'</label>
            ';
            $i++;
            if($i%3==0)$Mod.='</br>';
        }
        $Mod.='</br> <input type="submit" class="btn btn-primary" value="Valider"></form>';

        return $Mod;
    }
   static function CheckString(string $check,string $verif):string{
        if($check == $verif) return "selected";
        else return '';
    }
    static function CheckCombo(array|string $check=null,string $verif):string{
        if(is_array($check)){
            foreach ($check as $key => $value){
                if($value['idCour']==$verif)   return "checked";
            }
        }
       return '';
    }
    private static function DeleteFormation(): string
    {
        $COUR= new cour();
        $DataCombobox=$COUR->GetAllNameAndIdFromation();
        //------------------------------------------------------------------------
        $Mod= '
      <div class="col-sm-3">
      </div>
        <div class="col-sm-5">
            <div class="card">
                <div class="card-body">
                    <form action="index.php?view=api/Modal/RemoveFormationModal" method="post" enctype="multipart/form-data">
                        <select name="modal" id="modal" class="form-control" required>';

        //-------------------ComboBox---------------------------------------------

        foreach ($DataCombobox as $Key=>$Value){
            $Mod.= '<option value="'.$Value['idForma'].'">' .$Value['nameForma']. '</option>';
        }
        $Mod.='</select> <input type="submit" class="btn btn-primary" value="Valider"> </form> </br> 
            </div></div></div>';

        return $Mod;
    }
}



