const sections = document.querySelectorAll("section");
const navLi = document.querySelectorAll("nav .navbar-nav li a");
window.onscroll = () => {
  var current = "";

  sections.forEach((section) => {
    const sectionTop = section.offsetTop;
    if (pageYOffset >= sectionTop - 60) {
      current = section.getAttribute("id"); }
  });

  navLi.forEach((a) => {
    a.classList.remove("active");
    if (a.getAttribute("href") == "#"+current) {
      a.classList.add("active");
    }
  });
};