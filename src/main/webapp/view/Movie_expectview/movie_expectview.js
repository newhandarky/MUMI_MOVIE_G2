$(function () {

var str = sessionStorage.getItem("trailer");
console.log(str);
var index= str.indexOf("=",9) +1;
var str2 = str.slice(index)
var str3 = `https://www.youtube.com/embed/${str2}`;

var html = `<iframe width="560" height="315" src="${str3}"  title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>`;
$("body").append(html);
});
