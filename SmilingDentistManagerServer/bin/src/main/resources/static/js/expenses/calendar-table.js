Array.from(
    document.getElementsByClassName("sdm-calendar-td-link")
).forEach( hoverElement => {

    document.addEventListener('DOMContentLoaded', function() {
        // var hoverElement = document.getElementById('hoverElement');
        
        var resetBackgroundColor = hoverElement.style.backgroundColor;
        var resetTextColor = hoverElement.style.color;  

        hoverElement.addEventListener('mouseover', function() {
            hoverElement.style.backgroundColor = '#343a40';
            hoverElement.style.color = '#ffffff';
            hoverElement.style.cursor = 'pointer';
        });

        hoverElement.addEventListener('mouseout', function() {
            hoverElement.style.backgroundColor = resetBackgroundColor;
            hoverElement.style.color = resetTextColor;
        });
    });

});