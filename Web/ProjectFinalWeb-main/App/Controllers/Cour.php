<?php

namespace App\Controllers;


use App\Helpers\Bootstrap;
use App\Helpers\Output;
use App\Model\Role;
use http\Header;


require "App\Model\cour.php";

class Cour{

    function LoadTable(){
        $COUR = new \App\Model\cour();
        $data = $COUR->GetAllFromFromation();
        unset($data["idForma"]);
        $header=array(["Formation","Niveaux d'étude","Date de commencement","Date de fin","Inscrire"]);
        echo Bootstrap::tableCour($header,$data,'Liste de formation');
    }

    function InsertDemande(int $idUser,int $idCour){
        $Cour = new \App\Model\cour();
        $bol = $Cour->InsertDemande($idUser,$idCour);
        if($bol) Output::createAlert("Demande  envoyer", 'success', 'index.php?view=api/Cour/LoadTable');
        else Output::createAlert("Demande non envoyer", 'danger', 'index.php?view=api/Cour/LoadTable');
    }

    function ValidationDemande(string $txt,string $userName,string $NameFormation){
        $USER = new \App\Model\user();
        $COUR = new \App\Model\cour();
        $userID= $USER->GetUserID($userName);
        $courID = $COUR->GetIdFromation($NameFormation);

        if($txt=='Val'){
            $COUR->VerifBeforInscription($userID,$courID);
            $ROLE = new Role();
            $ROLE->GiveRoleByIdUser($userID,3);
        }elseif($txt=='Sup'){
            $COUR->RefuDemande($userID,$courID);
        }
        header('Location: index.php?view=api/Bootstrap/Admin/Demande/n/n');
    }

    function createCour(){
        $NomCour= htmlentities($_POST['Nom']);
        $prof =htmlentities($_POST['prof']);
        $COUR=new \App\Model\cour();
        $COUR->CreatCour($NomCour,$prof);
        Output::createAlert("Cour cree", 'success', 'index.php?view=api/Bootstrap/Admin/CreatCour/add/n');
    }

    function UpdateCour(String $cour=''){
        $COUR= new \App\Model\cour();
        $coure = $COUR->GetCourByName($cour);

        if(!empty($coure)){
           $bool=false;
           $query='Update cour set';

        if($coure[0]['courName']!=htmlentities($_POST['Nom'])){
            $query.="  courName='".htmlentities($_POST['Nom'])."',";
            $bool=true;
        }
        if($coure[0]['teacher']!=htmlentities($_POST['prof'])){
            $query.=" teacher='".htmlentities($_POST['prof'])."',";
            $bool=true;
        }
        if($coure[0]['statut']!=htmlentities($_POST['statut'])){
            $query.=" statut='".htmlentities($_POST['statut'])."',";
            $bool=true;
        }
        if($bool){
            $query= substr($query,0,-1);

            $query.= " where courName='".$coure[0]['courName']."'";
            $COUR->UpdateCour($query);
            Output::createAlert("Cour modifier","success","index.php?view=api/Bootstrap/Admin/CreatCour/up/n");
        }
        }
        header('Location:index.php?view=api/Bootstrap/Admin/CreatCour/up/n');
    }
    function DeleteCour(int $id){
        $COUR =new \App\Model\cour();
        $bool = $COUR->DeleteCour($id);
        if($bool) Output::createAlert('Cour supprimer','success','index.php?view=api/Bootstrap/Admin/CreatCour/del/n');
        else  Output::createAlert('Echec de la suppression','danger','index.php?view=api/Bootstrap/Admin/CreatCour/del/n');
    }

    function createFormation(){
        $COUR= new \App\Model\cour();
        $Post =$_POST;
        $dataFormation=[];
        $dataCour=[];
        foreach ($Post as $Key=>$value){
            if(is_int($Key))array_push($dataCour,htmlentities($value));
            else array_push($dataFormation,htmlentities($value));
        }
        $idFormation= $COUR->creatFormation($dataFormation);
        foreach ($dataCour as $value)$COUR->LinkCourFormation($idFormation,$value);
        Output::createAlert("Formation cree", 'success', 'index.php?view=api/Bootstrap/Admin/n/n/n');
    }

    function UpdateFormation(int $idFormation){
        $COUR= new \App\Model\cour();
        $Post =$_POST;

        $Formation = $COUR->GetFromationByid($idFormation);
        $LinkCour = $COUR->GetIdLinkCourFormation($idFormation);

        $FlagForma=false;
        $FlagCour=false;

        $dataCour=[];
        $Update='Update formation set ';

        foreach ($Post as $Key=>$value) if(is_int($Key))array_push($dataCour,$value);

        foreach ($dataCour as $item) if($item==$LinkCour[0]['idCour']) $FlagCour=true;

       if((!empty($_POST['Nom']))&&($Formation[0]['nameForma']!=htmlentities($_POST['Nom']))){
            $FlagForma=true;
            $Update.=" nameForma='".htmlentities($_POST['Nom'])."',";
        }
        if((!empty($_POST['LVEtude']))&&($Formation[0]['LVEtude']!=htmlentities($_POST['LVEtude']))){
            $FlagForma=true;
            $Update.=" LVEtude='".htmlentities($_POST['LVEtude'])."',";
        }
        if((!empty($_POST['dateStart']))&&($Formation[0]['dateStart']!=htmlentities($_POST['dateStart']))){
            $FlagForma=true;
            $Update.=" dateStart='".htmlentities($_POST['dateStart'])."',";
        }
        if((!empty($_POST['dateEnd']))&&($Formation[0]['dateEnd']!=htmlentities($_POST['dateEnd']))){
            $FlagForma=true;
            $Update.=" dateEnd='".htmlentities($_POST['dateEnd'])."',";
        }
        if((!empty($_POST['statut']))&&($Formation[0]['statue']!=htmlentities($_POST['statut']))){
            $FlagForma=true;
            $Update.=" statue='".htmlentities($_POST['statut'])."',";
        }
        if($FlagForma){
            $Update= substr($Update,0,-1);
            $Update.=" where idForma='".$idFormation."'";
            $COUR->UpdateCour($Update);
        }
        if($FlagCour){
            $COUR->DeleteLinkCourFormation($idFormation);
            foreach ($dataCour as $item) $COUR->LinkCourFormation($idFormation,$item);
        }
        if($FlagCour || $FlagForma) Output::createAlert("Update effectuer","success","index.php?view=api/Bootstrap/Admin/CreatFroma/up/n");
    }

    function DeleteFormation(int $idForma){
        $COUR =new \App\Model\cour();
        $bool = $COUR->DeleteFormation($idForma);
        if($bool) Output::createAlert('Formation supprimer','success','index.php?view=api/Bootstrap/Admin/CreatCour/del/n');
        else  Output::createAlert('Echec de la suppression','danger','index.php?view=api/Bootstrap/Admin/CreatCour/del/n');
    }



}