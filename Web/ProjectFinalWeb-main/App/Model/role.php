<?php

namespace App\Model;


use App\Helpers\DB;


class Role
{
    public function GiveRoleByUserName(string $UserName, int $Role): bool
    {

        $db = DB::conect();
        $exec = $db->prepare('select idUser from user where userName = ?');
        $exec->execute([$UserName]);
        $data_tmp = $exec->fetchAll();
        $exeRole = $db->prepare('insert into user_role(idUser,idRole) values (?,?)');
        $exeRole->execute([$data_tmp[0]['idUser'], $Role]);
        return false;
    }
    public function GiveRoleByIdUser(int $idUser, int $Role): bool
    {
        $db = DB::conect();
        $exeRole = $db->prepare('insert into user_role(idUser,idRole) values (?,?)');
        $exeRole->execute([$idUser, $Role]);
        return false;
    }

    function GetAllRole(): array
    {
        $db = DB::conect();
        $data=[];
        $exe = $db->prepare('select NameRole from role');
        $exe->execute();
        while ($data_tmp = $exe->fetchAll()){
            $data[]=$data_tmp;
        }
        return $data[0];
    }

    function CheckRole(int $idUser,int $idRole):bool{
        $db = DB::conect();
        $exe = $db->prepare('select id from user_role where idUser=? and idRole=?');
        $exe->execute([$idUser,$idRole]);
        if($exe->fetchColumn()) return true;
        else return false;
    }

    function GetRoleUser(int $idUser):array{
        $db = DB::conect();
        $data=[];
        $exe = $db->prepare('select NameRole from role inner join user_role where  user_role.idRole=role.idRole and idUser=?');
        $exe->execute([$idUser]);
        while ($data_tmp = $exe->fetchAll()){
            $data[]=$data_tmp;
        }

        return $data[0];
    }
    function GetIDRole(String $nameRole):int{
        $db = DB::conect();
        $data=[];
        $exe = $db->prepare('select idRole from role  where  NameRole=?');
        $exe->execute([$nameRole]);
        while ($data_tmp = $exe->fetchAll()){
            $data[]=$data_tmp;
        }
        return $data[0][0]['idRole'];

    }
    function RemoveRole(int $idUSer,int $idRole){
        $db = DB::conect();
        $exe = $db->prepare('delete from user_role where  idUser=? and idRole=?');
        $exe->execute([$idUSer,$idRole]);
    }
}
