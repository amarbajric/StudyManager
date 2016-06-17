var opts = {
    lines: 12, // The number of lines to draw
    angle: 0, // The length of each line
    lineWidth: 0.4, // The line thickness
    pointer: {
        length: 0.75, // The radius of the inner circle
        strokeWidth: 0.042, // The rotation offset
        color: '#1D212A' // Fill color
    },
    limitMax: 'false', // If true, the pointer will not go past the end of the gauge
    colorStart: '#1ABC9C', // Colors
    colorStop: '#1ABC9C', // just experiment with them
    strokeColor: '#F0F3F3', // to see which ones work best for you
    generateGradient: true
};
var target = document.getElementById('analog_ects'); // your canvas element
var gauge = new Gauge(target).setOptions(opts);
var current_ects = parseFloat(document.getElementById("current_ects").innerText);
gauge.maxValue = 180; // set max gauge value
gauge.animationSpeed = 32; // set animation speed (32 is default value)
gauge.set(current_ects); // set actual value
gauge.setTextField(document.getElementById("current_ects"));
