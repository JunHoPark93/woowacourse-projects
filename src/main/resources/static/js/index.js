window.addEventListener('scroll', function (evt) {
    "use strict";

    const header = document.getElementById("header");
    const logoDark = document.querySelector(".logo-dark");
    const logoWhite = document.querySelector(".logo-white")
    const userInfo = document.querySelector(".user-info span")

    if (window.pageYOffset > 10) {
        header.classList.remove("no-border")
        header.classList.add("bg-white", "border", "bottom")
        logoDark.classList.remove("d-none")
        logoWhite.classList.add("d-none")
        userInfo.classList.remove("text-white")
        userInfo.classList.add("text-dark")
    } else {
        header.classList.add("no-border")
        header.classList.remove("bg-white", "border", "bottom")
        logoDark.classList.add("d-none")
        logoWhite.classList.remove("d-none")
        userInfo.classList.add("text-white")
        userInfo.classList.remove("text-dark")
    }
});