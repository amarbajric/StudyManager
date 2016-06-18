$(document.getElementById('canvasDataCircle')).ready(function() {

Chart.defaults.global.legend = {
      enabled: false
    };

    var data = {
      labels: [
        "Excellent",
        "Good",
        "Satisfactory",
        "Sufficient",
        "Not Sufficient"
      ],
      datasets: [{
        data: [parseInt(document.getElementById("Excellent").innerText), parseInt(document.getElementById("Good").innerText), parseInt(document.getElementById("Satisfactory").innerText), parseInt(document.getElementById("Sufficient").innerText), parseInt(document.getElementById("NotSufficient").innerText)],
        backgroundColor: [
          "#3498DB",
          "#1ABB9C",
          "#9B59B6",
          "#9CC2CB",
          "#E74C3C"
        ],
        hoverBackgroundColor: [
          "#5cace2",
          "#47c8af",
          "#af7ac4",
          "#afced5",
          "#eb6f62"
        ]
      
      }]
    }

    var canvasDoughnut = new Chart(document.getElementById('canvasDataCircle'), {
      type: 'doughnut',
      tooltipFillColor: "rgba(51, 51, 51, 0.55)",
      data: data,
      beginAtZero:true,
      step:5
    })});