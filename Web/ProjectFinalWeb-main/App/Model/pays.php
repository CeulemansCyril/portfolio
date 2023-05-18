<?php

namespace App\Model;

use App\Helpers\DB;

require __DIR__ . '/../Helpers/DB.php';

class pays
{
    function GetAllContrie(): array
    {
        $db = DB::conect();
        $data = [];
        $exe = $db->query('select PaysName from pays order by PaysName');
        while ($data_tmp = $exe->fetchAll()){
            $data[]=$data_tmp;
        }
        return $data[0][0];
    }

    function AddContrie(string $Name): bool
    {
        $db = DB::conect();
        $exe = $db->prepare('insert into pays (PaysName) values (?)');
        $exe->execute([$Name]);
        if ($exe->fetchColumn()) return true;
        return false;
    }
}