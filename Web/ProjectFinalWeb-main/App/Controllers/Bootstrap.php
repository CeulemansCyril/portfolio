<?php

namespace App\Controllers;

use App\Helpers\Output;

class Bootstrap{
    function Admin(String $show=null,string $action=null,string $CourForma=null){
        Output::renderAdmin($show,$action,$CourForma);
    }

    function AdminUpdateUSer(int $idUser){
        Output::render('UpdateUser',$idUser);
    }
    function UpdateCour(){
        $cour = htmlentities($_POST["modal"]);
        Output::renderAdmin('CreatCour','up',$cour);
    }
    function UpdateForma(){
        $NameForma = htmlentities($_POST["modal"]);
        $COUR = new \App\Model\cour();
        $cour = $COUR->GetIdFromation($NameForma);
        Output::renderAdmin('CreatFroma','up',$cour);
    }
}