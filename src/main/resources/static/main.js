var header = 'Fruit Review';
document.querySelector('h3').innerText = header;

fetch('/fruit').then(resp => resp.json()).then(json => document.getElementById('fruitReviews').innerHTML = displayAllReviews(json));

let displayFruitReview = function(fruitJson) {
    return '<p>' + fruitJson.reviewId + ", " + fruitJson.fruitName + ", " + fruitJson.rating + ", " + fruitJson.review;
};

let displayAllReviews =  function(json) {
    return `
        <div id="fruitList">
            ${json.map(displayFruitReview).join('\n')}
        </div>
    `
};

let postReview = function() {
    let fruitReview = {
        "reviewId": document.getElementById('reviewId').value,
        "fruitName": document.getElementById('fruitName').value,
        "rating": document.getElementById('rating').value,
        "review": document.getElementById('review').value
    }
    console.log(fruitReview);
    fetch('/fruit', {
        method: "POST",
        headers: {
            'Accept': 'application.json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(fruitReview)
    }).then(result => console.log(result.text()));
    return false;
};