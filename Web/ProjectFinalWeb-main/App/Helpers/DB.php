<?php

namespace App\Helpers;

use PDO;
use PDOException;

class DB{
    protected static ?PDO $db = null;

    public static function conect(){
        if(!self::$db){
            try {
                require_once __DIR__ . '/../../config.php';
                self::$db = new PDO('mysql:dbname=' . DB_NAME . ';host=' . DB_HOST . ';charset=utf8', DB_USER, DB_PASSWORD);
                self::$db->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);
            }catch (PDOException $exception){
                //if the database don't exist we catch the error and created the database
                if(str_contains($exception->getMessage(),'SQLSTATE[HY000]')){
                    $dbh = new PDO('mysql:host='.DB_HOST.';charset=utf8',DB_USER,DB_PASSWORD);
                    $dbh->query('create database if not EXISTS '.DB_NAME);
                    self::$db=self::conect();
                    return self::$db;
                }
                die('Erreur : '.$exception->getMessage());
            }
        }
        return self::$db;
    }
}
