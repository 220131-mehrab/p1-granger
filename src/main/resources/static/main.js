var header = 'Fruit Review';
document.querySelector('h3').innerText = header;

fetch('/fruit').then(resp => resp.json()).then(json => document.getElementById('fruitReviews').innerHTML = displayAllReviews(json));

let displayFruitReview = function(fruitJson) {
    return '<p>' + fruitJson.id + ", " + fruitJson.fruitName + ", " + fruitJson.rating + ", " + fruitJson.review;
};

let displayAllReviews =  function(json) {
    return `
        <div id="fruitList">
            ${json.map(displayFruitReview).join('\n')}
        </div>
    `
};