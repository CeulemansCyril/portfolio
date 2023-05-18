<?php

namespace App\Helpers;

use App\Model\Role;
use App\Model\user;



class Acess{
    public static function CheckUserSession(int $id):void{
        if((empty($_SESSION['userid'])) || ($_SESSION['userid']!=$id)){
            header('Location : index.php?view=View/User/Login');
            die;
        }
    }

    public static function  CheckUserMailOrLogin(String $user ='',String $mail=''):bool{
        $userModel = new user();
        return $userModel->VerifUserNameOrMail($user,$mail);
    }

    public static function isAdmin():bool{
        $role = new Role();
        if(empty($_SESSION)) Output::createAlert('Erreur de connection','danger');
        return $role->CheckRole($_SESSION['userid'],1);
    }
    public static function isBan($idUser):bool{
        $role = new Role();
        return $role->CheckRole($idUser,5);
    }
}