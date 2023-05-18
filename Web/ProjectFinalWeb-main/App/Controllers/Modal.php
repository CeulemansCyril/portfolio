<?php

namespace App\Controllers;

use App\Helpers\Output;
use App\Model\user;

//classe permanent d'appeler le modal de suppression
class Modal{

    function RemoveUserModal(int $id=null){
        $USER = new user();
        $titleModal = $USER->GetUserNameByUserId($id);
        Output::renderModal("ModalPopUp",$titleModal[0]['userName'],$id,'index.php?view=api/user/DeleteUser/','index.php?view=api/Bootstrap/Admin/ListUser/n/n');
    }
    function RemoveCourModal(){
        $COUR =new \App\Model\cour();
        $id =htmlentities($_POST['modal']);
        $courName = $COUR->GetCourNameById($id);
        Output::renderModal("ModalPopUp",$courName[0]['courName'],$id,'index.php?view=api/cour/DeleteCour/','index.php?view=api/Bootstrap/Admin/CreatCour/del/n');
    }
    function RemoveFormationModal(){
        $COUR =new \App\Model\cour();
        $id = htmlentities($_POST['modal']);
        $courName = $COUR->GetFromationByid($id);
        Output::renderModal("ModalPopUp",$courName[0]['nameForma'],$id,'index.php?view=api/cour/DeleteFormation/','index.php?view=api/Bootstrap/Admin/CreatFroma/del/n');
    }

}