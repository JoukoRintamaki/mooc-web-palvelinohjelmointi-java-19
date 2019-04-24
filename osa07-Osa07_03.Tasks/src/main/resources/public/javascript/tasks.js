var url = contextRoot + "tasks"

var http = new XMLHttpRequest()

http.onreadystatechange = function () {
    if (this.readyState != 4 || this.status != 200) {
        return
    }

    var tasks = JSON.parse(this.responseText)
    document.getElementById("tasks").innerHTML = ""
    tasks.forEach(function (task) {
            document.getElementById("tasks").innerHTML += "<p>" + task.name + "</p>"
        }
    )
}

function getTasks() {
    http.open("GET", url)
    http.send()
}

function laheta() {
    var task = {
        name: document.getElementById("text").value,
    }
    http.open("POST", url)
    http.setRequestHeader("Content-Type", "application/json")
    http.send(JSON.stringify(task))
}