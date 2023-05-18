<main role="main" class="container-fluid">
<?php

use App\Helpers\Output;

require 'App/Helpers/Output.php';

Output::manageAlerts();

if(!empty($_GET['view']) && $_GET['view']!='View/Default'){
    try {

        Output::getContent($_GET['view']);
    }catch (\Exception $e){
        Output::render('messageBox', 'Veuillez utiliser l\'interface du site','danger');
    }


}else{
    Output::render('messageBox', 'Bienvenue','success');
}

?>
</main>
