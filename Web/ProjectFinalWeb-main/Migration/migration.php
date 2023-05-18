<?php

use App\Helpers\DB;

require __DIR__ . '/../config.php';
require __DIR__ . '/../App/Helpers/DB.php';


$Para=$_SERVER['argv'];

$help = ['--help', '-help', '-h', '-?', 'h', 'help', '?'];

if ($_SERVER['argc'] != 2 || in_array($Para[1], $help)) {
    echo "Cette commande permet de migrer les fichiers SQL présents dans le dossier migration\n
    Cette commande comporte un paramètre, pouvant contenir les valeurs suivantes :
    --help|-help|-h|-?|h|help|? pour afficher cette aide
    --all pour migrer tous les fichiers SQL présents dans le dossier migration, dans le sens inverse de l'ordre alphabétique.
    --update pour migrer les fichiers SQL présents dans le dossier migration et plus récents que la date de la dernière migration, du plus ancien au plus récent
    <filename> le nom du fichier SQL à exécuter (le paramètre ne contient pas l'extension du fichier).\n 
    Ce fichier doit être présent dans le dossier migration\n
    Exemples de commande :\n
    php migration.php user\n
    php migration.php --all\n
    php migration.php --update\n
    Cette commande exécutera le code SQL présent dans le(s) fichier(s) SQL";
    die;
}


if($Para[1] == '--all'){
    MigrationAll();
}elseif ($Para[1] == '--update'){
    $db = DB::conect();
    if ($db->query("SHOW TABLES LIKE 'migration'")) {
        $lastUp=getlastUpdate();

        if(empty($lastUp)){
            setlastUpdate();
        }
        MigrationAll(true);
    }else{
        OneMigration('migration');
        setlastUpdate();
        MigrationAll(true);
    }
}else{
    $File = $Para[1].'.sql';
    OneMigration($File);
    setlastUpdate();
}

function MigrationAll(bool $time=false){
   if($time) $fileTime = getlastUpdate();
    $File=[];
    $Path = getcwd();
    $AllFile=scandir($Path);
    foreach ($AllFile as $key){
        if(pathinfo($key,PATHINFO_EXTENSION)=='sql'){
            if($time){
                $timeFile = filemtime($Path.'/'.$key);
                if(($fileTime=='')||($timeFile>$fileTime)){
                    $File[$key]= $key;
                }
            } $File[$key]= $key;
        }
    }
    arsort($File);
    foreach ($File as $KeyFile){
        OneMigration($KeyFile);
    }
}

function OneMigration(String $NameFile){
    $path = getcwd().'/'.$NameFile;
    if(file_exists($path)){
        $ContenentPath = file_get_contents($path);
        if(CheckString($ContenentPath)){
            echo "Le fichier ". $NameFile ." vas être executer\n";
            Migration($ContenentPath);
        }else{
            echo "Le fichier ".$NameFile." contient une command sql non autoriser\n";
        }

    }
}

function CheckString (String $txt): bool{
    $Check=strtolower($txt);
    if(str_contains($Check, 'delete')){
        if(str_contains($Check, 'on delete cascade')) return true;
        else return false;
    }
    elseif(str_contains($Check,'drop')) return false;
    elseif(str_contains($Check,'truncate')) return false;
    elseif(str_contains($Check,'rename')) return false;
    return true;
}
function Migration(String $sql){
    $DB = DB::conect();
    $DB->query($sql);
}
function getlastUpdate(): ?string{
    $db = DB::conect();
    $data = [];
    $exe= $db->prepare('select lastUpdate from migration where id=1');
    while ($data_tmp=$exe->fetchObject()){
        $data[]=$data_tmp;
    }
    if(!empty($data))return $data['lastUpdate'] ;
    else return null;

}
function setlastUpdate(){
;
    $db = DB::conect();
    $db->query("Update migration set lastUpdate = NOW() where id=1;");
}