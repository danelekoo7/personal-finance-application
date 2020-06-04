$(document).ready(function(){


    // Toggle the side navigation
    $("#sidebarToggle ").on("click", function (e) {
        console.log("#sidebarToggle")
        e.preventDefault();
        $("body").toggleClass("sb-sidenav-toggled");
    });

    // // Toggle the side navigation
    // $("#layoutSidenav_nav .sb-sidenav a.nav-link").on("click", function (e) {
    //     console.log("#layoutSidenav_nav .sb-sidenav a.nav-link")
    //     e.preventDefault();
    //     $(this).toggleClass("active");
    // });
});