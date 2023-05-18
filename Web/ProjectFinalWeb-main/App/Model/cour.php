<?php

namespace App\Model;

use App\Helpers\DB;


class cour
{
    //-------------------------------------------Cour------------------------------------------------
    function CreatCour(string $Name, string $teacher = ''): bool
    {
        if ($this->CheckCourName($Name)) {
            $db = DB::conect();
            $exe = $db->prepare('insert into cour(courName,teacher) values (?,?)');
            $exe->execute([$Name, $teacher]);
            if ($exe->fetchColumn()) return true;
            return false;
        }
        return false;
    }

    public function CheckCourName(string $Name)
    {
        $db = DB::conect();
        $exe = $db->prepare('select idCour from cour where courName=?');
        $exe->execute([$Name]);
        if ($exe->fetchColumn()) return false;
        return true;
    }

    function UpdateCour(string $query)
    {
        $db = DB::conect();
        $exe = $db->prepare($query);
        $exe->execute();
    }

    function DeleteCour(int $idCour): bool
    {
        if ($this->CheckCour($idCour)) {
            $db = DB::conect();
            $exe = $db->prepare("delete from cour where idCour=?");
            $exe->execute([$idCour]);
            if ($exe->fetchColumn()) return false;
            return true;
        }
        return false;
    }

    function CheckCour(int $idCour)
    {
        $db = DB::conect();
        $exe = $db->prepare("select id from cour_formation where idCour=?");
        $exe->execute([$idCour]);
        if ($exe->fetchColumn()) return false;
        return true;
    }

    function GetAllCourIdFormation(int $idForma): ?array
    {
        $db = DB::conect();
        $data = [];
        $exe = $db->prepare('select cour.idCour from cour inner join cour_formation where cour_formation.idCour=cour.idCour and cour_formation.idForma=?');

        $exe->execute([$idForma]);
        while ($data_tmp = $exe->fetchAll()) {
            $data[] = $data_tmp;
        }
        if (!empty($data)) return $data[0];
        else return null;
    }

    function GetCourByName(string $Name): ?array
    {
        $db = DB::conect();
        $data = [];
        $exe = $db->prepare('select courName,statut,teacher from cour where courName=?');

        $exe->execute([$Name]);
        while ($data_tmp = $exe->fetchAll()) {
            $data[] = $data_tmp;
        }
        if (!empty($data)) return $data[0];
        else return null;
    }

    function GetCourNameById(int $id): ?array
    {
        $db = DB::conect();
        $data = [];
        $exe = $db->prepare('select courName from cour where idCour=?');

        $exe->execute([$id]);
        while ($data_tmp = $exe->fetchAll()) {
            $data[] = $data_tmp;
        }
        if (!empty($data)) return $data[0];
        else return null;
    }

    function GetAllCour(): array
    {
        $db = DB::conect();
        $data = [];
        $exe = $db->prepare('select courName,idCour from cour');
        $exe->execute();
        while ($data_tmp = $exe->fetchAll()) {
            $data[] = $data_tmp;
        }
        return $data[0];
    }

    function GetAllCourActif(): array
    {
        $db = DB::conect();
        $data = [];
        $exe = $db->prepare('select courName from cour where statut=true');
        $exe->execute();
        while ($data_tmp = $exe->fetchAll()) {
            $data[] = $data_tmp;
        }
        return $data[0];
    }

    function GetAllCourInActif(): array
    {
        $db = DB::conect();
        $data = [];
        $exe = $db->prepare('select courName from cour where statut=false');
        $exe->execute();
        while ($data_tmp = $exe->fetchAll()) {
            $data[] = $data_tmp;
        }
        return $data[0];
    }

    //-------------------------------------------Formation------------------------------------------------
    function creatFormation(array $data): int
    {
        $db = DB::conect();
        $exe = $db->prepare('insert into formation (nameForma,LVEtude,statue,dateStart,dateEnd) values (?,?,true,?,?)');
        $exe->execute($data);
        return $db->lastInsertId();
    }

    function DeleteFormation(int $id): bool
    {
        if ($this->CheckInscritForma($id)) {
            $db = DB::conect();
            $exe = $db->prepare("delete from formation where idForma=?");
            $exe->execute([$id]);
            self::DeleteLinkCourFormation($id);
            if ($exe->fetchColumn()) return false;
            return true;
        }
        return false;
    }

    function CheckInscritForma(int $id)
    {
        $db = DB::conect();
        $exe = $db->prepare("select id from user_formation where idForma=?");
        $exe->execute([$id]);
        if ($exe->fetchColumn()) return false;
        return true;
    }

    function GetFromationByid(int $idForma)
    {
        $db = DB::conect();
        $data = [];
        $exe = $db->prepare('select * from formation where idForma=?');
        $exe->execute([$idForma]);
        while ($data_tmp = $exe->fetchAll()) {
            $data[] = $data_tmp;
        }
        return $data[0];
    }

    function GetAllNameFromation(): array
    {
        $db = DB::conect();
        $data = [];
        $exe = $db->prepare('select nameForma from formation');
        $exe->execute();
        while ($data_tmp = $exe->fetchAll()) {
            $data[] = $data_tmp;
        }
        return $data[0];
    }

    function GetAllNameAndIdFromation(): array
    {
        $db = DB::conect();
        $data = [];
        $exe = $db->prepare('select idForma,nameForma from formation');
        $exe->execute();
        while ($data_tmp = $exe->fetchAll()) {
            $data[] = $data_tmp;
        }
        return $data[0];
    }

    function GetAllFromFromation(): array
    {
        $db = DB::conect();
        $data = [];
        $exe = $db->prepare('select * from formation');
        $exe->execute();
        while ($data_tmp = $exe->fetchAll()) {
            $data[] = $data_tmp;
        }
        return $data[0];
    }

    function GetAllFromationUser(int $idUSer): mixed
    {
        $db = DB::conect();
        $data = [];
        $exe = $db->prepare('select nameForma from formation inner join user_formation where user_formation.idForma=formation.idForma
        and idUser=?');
        $exe->execute([$idUSer]);
        while ($data_tmp = $exe->fetchAll()) {
            $data[] = $data_tmp;
        }
        if (!empty($data)) return $data[0];
        else return '';

    }

    function GetIdFromation(string $NameForma): int
    {
        $db = DB::conect();
        $data = [];
        $exe = $db->prepare('select idForma from formation where nameForma=?');
        $exe->execute([$NameForma]);
        while ($data_tmp = $exe->fetchAll()) {
            $data[] = $data_tmp;
        }

        return $data[0][0]['idForma'];
    }

    function ExcluFormation(int $idUser, int $idFroma)
    {
        $db = DB::conect();
        $exe = $db->prepare('delete from user_formation where idUser=? and idForma=?');
        $exe->execute([$idUser, $idFroma]);
    }

    //-------------------------------------------Demande------------------------------------------------
    function CheckDemande(int $idUser, int $idFormation): bool
    {
        $db = DB::conect();
        $exe = $db->prepare('select idDemande from demande where idUser=? and idFormation=? and statue=true');
        $exe->execute([$idUser, $idFormation]);
        if ($exe->fetchColumn()) return true;
        else return false;
    }

    function CheckDemandeRefu(int $idUser, int $idFormation): bool
    {
        $db = DB::conect();
        $exe = $db->prepare('select idDemande from demande where idUser=? and idFormation=? and statue=false');
        $exe->execute([$idUser, $idFormation]);
        if ($exe->fetchColumn()) return true;
        else return false;
    }

    function CheckInscrit(int $idUser, int $idFormation): bool
    {
        $db = DB::conect();
        $exe = $db->prepare('select id from user_formation where idUser=? and idForma=?');
        $exe->execute([$idUser, $idFormation]);
        if ($exe->fetchColumn()) return true;
        else return false;
    }

    function InsertDemande(int $idUser, int $idFormation): bool
    {
        $db = DB::conect();
        $exe = $db->prepare('insert into demande(idUser,statue,idFormation) values (?,true,?)');
        $exe->execute([$idUser, $idFormation]);
        if ($exe->fetchColumn()) return false;
        else return true;
    }

    function RefuDemande(int $idUser, int $idFormation): bool
    {
        $db = DB::conect();
        $exe = $db->prepare('Update demande set statue=false where idUser=? and idFormation=?');
        $exe->execute([$idUser, $idFormation]);
        if ($exe->fetchColumn()) return true;
        else return false;
    }

    function DeletDemande(int $idUser, int $idFormation): bool
    {
        $db = DB::conect();
        $exe = $db->prepare('Delete from demande where idUser=? and idFormation=?');
        $exe->execute([$idUser, $idFormation]);
        if ($exe->fetchColumn()) return true;
        else return false;
    }

    function VerifBeforInscription(int $idUser, int $idFormation): bool
    {
        if (($this->CheckDemande($idUser, $idFormation)) || ($this->CheckDemandeRefu($idUser, $idFormation))) {
            $this->DeletDemande($idUser, $idFormation);
            return $this->Inscription($idUser, $idFormation);
        } else {
            return $this->Inscription($idUser, $idFormation);
        }
    }

    function Inscription(int $idUser, int $idFormation): bool
    {
        $db = DB::conect();
        $exe = $db->prepare('insert into user_formation(idUser,idForma,dateInscription) values (?,?,now())');
        $exe->execute([$idUser, $idFormation]);
        if ($exe->fetchColumn()) return true;
        else return false;
    }

    function GetAllDemandeAttendUserFormation(): ?array
    {
        $db = DB::conect();
        $data = [];
        $exe = $db->prepare('select user.userName,nameForma from demande inner join user inner join formation where user.idUser=demande.idUser and formation.idForma=demande.idFormation and demande.statue=1');
        $exe->execute();
        while ($data_tmp = $exe->fetchAll()) {
            $data[] = $data_tmp;
        }
        if (!empty($data)) return $data[0];
        else return null;
    }

    function GetFormationByLike(string $text): ?array
    {
        $db = DB::conect();
        $data = [];
        $exe = $db->prepare("select nameForma from formation where nameForma like ?");
        $like = '%' . $text . '%';
        $exe->execute([$like]);
        while ($data_tmp = $exe->fetchAll()) {
            $data[] = $data_tmp;
        }
        if (!empty($data)) return $data[0];
        else return null;
    }

    //--------------------------------------------------CourFormation--------------------------------
    function LinkCourFormation(int $idFormation, int $idCour)
    {
        $db = DB::conect();
        $exe = $db->prepare('insert into cour_formation (idCour,idForma) values (?,?)');
        $exe->execute([$idCour, $idFormation]);
    }

    function GetIdLinkCourFormation(int $idFormation): ?array
    {
        $db = DB::conect();
        $exe = $db->prepare('select idCour from cour_formation where idForma=?');
        $exe->execute([$idFormation]);
        while ($data_tmp = $exe->fetchAll()) {
            $data[] = $data_tmp;
        }
        if (!empty($data)) return $data[0];
        else return null;
    }

    function DeleteLinkCourFormation(int $idFormation)
    {
        $db = DB::conect();
        $exe = $db->prepare('delete from cour_formation where idForma=?');
        $exe->execute([$idFormation]);
    }
}