var url = contextRoot + "books/random"

var http = new XMLHttpRequest()

http.onreadystatechange = function () {
    if (this.readyState != 4 || this.status != 200) {
        return
    }

    var response = JSON.parse(this.responseText)
    document.getElementById("title").innerText = response.title
    document.getElementById("author").innerText = response.author
    document.getElementById("pages").innerText = response.pages
}

function getRandomBook() {
    http.open("GET", url)
    http.send()
}