<?php

use App\Helpers\DB;



session_name('Web'.date('Ymd'));
session_start(['cookie_lifetime'=>3600]);

spl_autoload_register(function ($class) {
    require __DIR__ . '/' . str_replace('\\', DIRECTORY_SEPARATOR, $class). '.php';

});

require __DIR__ . '/View/Head.html';
require __DIR__ . '/View/Menu.php';
require __DIR__ . '/View/Default.php';
require __DIR__ . '/View/Footer.html';

