
$("#chartreuse").on("click", function (){
    $('#color').val(1);
});

    $(".view").on("dblclick", function (){
        let view = $(this).attr("getPostId");
        window.location.replace("http://localhost:8080/posts/show/" + view)
        console.log("clicked")
    });
