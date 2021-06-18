function AddController() {
  var ct = document.getElementById("Controller");
  var li = document.createElement("li");
  li.setAttribute("class", "nav-item");
  {
    var a = document.createElement("a");
    a.href = "myproducts.html";
    a.setAttribute("class", "nav-link");
    {
      var img = document.createElement("img");
      img.src = "css/product2.png";
      img.setAttribute("width", "30px");
      a.appendChild(img);

      var p = document.createElement("p");
      p.innerHTML = "我的商品";
      a.appendChild(p);
    }
    li.appendChild(a);
  }
  ct.appendChild(li);
}