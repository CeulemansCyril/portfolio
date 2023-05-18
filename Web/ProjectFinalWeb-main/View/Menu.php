

<div class="container">
    <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
        <a href="index.php?view=View/Default" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
            <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"/></svg>
            <span class="fs-4">Projet web</span>
        </a>

        <ul class="nav nav-pills">
            <li class="nav-item"><a href="index.php?view=View/Default" class="nav-link active" aria-current="page">Home</a></li>
            <?php  if(!empty($_SESSION['userid'])) {  ?>
                <li class="nav-item"><a href="index.php?view=api/cour/LoadTable" class="nav-link">Liste de cour</a></li>
                 <?php echo '<li class="nav-item"><a href="index.php?view=api/user/Profile/'. $_SESSION['userid'].'" class="nav-link">Profile</a></li>';

                 if($_SESSION['admin']) { ?>
                 <li class="nav-item"><a href="index.php?view=api/Bootstrap/Admin/n/n/n" class="nav-link">Admin</a></li>
              <?php   } ?>
                <li class="nav-item"><a href="index.php?view=api/User/logout" class="nav-link">LogOut</a></li>
            <?php }else{ ?>
                <li class="nav-item"><a href="index.php?view=View/User/Login" class="nav-link">LogIn</a></li>
                <li class="nav-item"><a href="index.php?view=View/User/Singup" class="nav-link">Sing up</a></li>
            <?php } ?>
        </ul>
    </header>
</div>
