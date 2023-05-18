<?php

namespace App\Helpers;

use Exception;
use JetBrains\PhpStorm\NoReturn;
use ReflectionException;
use ReflectionMethod;

class Output
{
    //Routing
    /**
     * @throws ReflectionException
     */
    public static function getContent(string $view): void
    {
        $fileType = array([1 => "html", 2 => "php"]);
        $route = parse_url($_SERVER['REQUEST_URI']);
        if (str_contains($route['query'], 'view=api/')) {
            $param = [];
            $split = explode('/', rtrim($route['query'], '/'));
            foreach ($split as $key => $value) {
                if ($key == 0) {
                    continue;
                } elseif ($key == 1) {
                    $class = 'App\Controllers\\' . ucfirst($value);
                } elseif ($key == 2) {
                    $methode = $value;
                } else {
                    $param[] = $value;
                }
            }

            if (!empty($class) && !empty($methode)) {
                $rou = new ReflectionMethod($class, $methode);
                $nb = $rou->getNumberOfParameters();
                if ($nb != count($param)) {
                    throw new Exception("Parameter number invaluable");
                }
                $controller = new $class();
                is_callable($methode, true, $callable_name);
                $controller->{$callable_name}(...array_values($param));
            }
        } elseif (!empty($view)) {

            foreach ($fileType as $key) {
                if ($key == 1) $text = $key[0];
                else $text = $key[1];
                $FullPath = $view . '.' . $text;
                if (file_exists($FullPath)) {
                    include_once $FullPath;
                }
            }

        } else {
            header('Location: index.php?View=View/Default');
            die;
        }

    }

    #[NoReturn] public static function createAlert(string $text, string $level = 'info', string $redirect = 'index.php?view=view/Default'): void
    {
        $_SESSION['alert'] = $text;
        $_SESSION['alert_level'] = $level;
        header('Location: ' . $redirect);

    }
    public static function manageAlerts(): void
    {
        if (!empty($_SESSION['alert']) && !empty($_SESSION['alert_level'])) {
            echo '<div class="alert alert-' . self::checkAlertLevel() . '">' . $_SESSION['alert'] . '</div>';
            unset($_SESSION['alert']);
            unset($_SESSION['alert_level']);
        }
    }

    public static function checkAlertLevel(): string
    {

        if (!empty($_SESSION['alert_level'])) {
            // Principe de la "white list"
            if (in_array($_SESSION['alert_level'], ['success', 'danger', 'info'])) {
                return $_SESSION['alert_level'];
            } else {
                return 'info';
            }
        }
        return '';
    }


    public static function render(string $template, object|array|string $data =null, string $class = 'danger')
    {
        echo Bootstrap::$template($data, $class);
    }
    public static function renderModal(string $template, String $titleModal=" ",int $id=null,string $GoOn,string $GoBack)
    {
        echo Bootstrap::$template($titleModal,$id,$GoOn,$GoBack);
    }
    public static function renderAdmin(string $show, String $action,string $CourForma)
    {
        echo Bootstrap::Admin($show,$action,$CourForma);
    }
}