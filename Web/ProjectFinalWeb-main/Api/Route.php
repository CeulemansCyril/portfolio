<?php
$path = "Api/Route/";

$route = parse_url($_SERVER['REQUEST_URI']);
//routing
if (str_contains($route['path'],$path)){

    spl_autoload_register(function ($class) {

        require __DIR__ . '/../' . strtolower(str_replace('\\', DIRECTORY_SEPARATOR, $class)) . '.php';
    });

    $param =[];
     $sizeFile =(strpos($route['path'],$path));

    $route = substr($route['path'], strpos($route['path'], strlen($path)) + $sizeFile, strlen($route['path']));

    $split = explode('/', rtrim($route, '/'));

    foreach ($split as $key => $value) {
        if ($key == 2) {
            $class = 'App\Controllers\\' . ucfirst($value);
        } elseif ($key == 3) {
            $methode = $value;
        } elseif ($key >= 4) {
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


}