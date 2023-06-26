function myFunction() {
  var popup = document.getElementById("myPopup");
  popup.classList.toggle("show");
}


document.addEventListener("DOMContentLoaded", () => {
  getNewWord();
  document.getElementById("loading-animation").style.display = "none";
    
  let guessedWords = [[]];
  let currentWord;
  var currentLetterId = 0;
  let size;
  let check;
  var wordSize;
  let currentRow = 0;
  const keys = document.querySelectorAll(".keyboard-row button");
  const container = document.getElementById("container");
  const gameBoard = document.getElementById("board");
  let successMessages = ["Magnificent","Impressive","Splendid","Great","Phew","Phew","Phew"]


  
  function getNewWord() {
    fetch(
      `http://localhost:8080/api/words/get`,
      {
        method: "GET",
      }
    )
      .then((response) => {
        return response.json();
      })
      .then((res) => {
        word = res.data;
        size = word.name.length;
        wordSize = size;
        makeRows(size+1,size);
        createSquares(size);
        currentWord= "";
      })
      .catch(() => {
        window.alert("Word is not recognised!");
      });
  }
  
  function createSquares(size) {
    for (let index = 0; index < size * (size+1); index++) {
      let square = document.createElement("div");
      square.classList.add("square");
      
      square.setAttribute("id", index + 1);
      gameBoard.appendChild(square);
    }
  }

  function addLetter(letter,size) {
    if(letter == 'x' || letter == 'w' || letter == 'q' ){
      return;
    }

    if(currentWord.length  < size){
      document.getElementById(currentLetterId + 1).innerText = letter;
      currentWord += letter;
      
      currentLetterId++;
    }
  }

  function deleteLetter(){
    if(currentWord.length != 0){
      document.getElementById(currentLetterId).innerText = "";
      currentWord = currentWord.slice(0, -1);
      currentLetterId--;
    }
  }


  function checkWord(size){
    if(currentWord.length < size) {
        shake("Not enough letters");
    }
    if(currentWord.length == size){
      fetch(
        `http://localhost:8080/api/words/check?name=${currentWord}`,
        {
          method: "GET",
        }
      )
        .then((response) => {
          return response.json();
        })
        .then((res) => {
          if(res.data ==null){
            console.log("Böyle bir kelime yok.");
            shake("Not in word list");
          }
          else{
            check = res.data;
            checkUtilitiy(check,size);
            
            if(checkAnswer(check)){
              shake(successMessages[currentRow]);
              removeEventListener("keydown",handleKeyDown);
            }
            currentRow++;

            
          }
        })
        .catch(() => {
          window.alert("Word is not recognised!");
        });
    }
  }
  const timer = ms => new Promise(res => setTimeout(res, ms))
  async function checkUtilitiy(myData,size){
      for(let i = 0; i < size; i++){
          document.getElementById(currentLetterId - size + 1+i).classList.add(myData.charAt(i)); 
          if(document.querySelector(`[data-key='${currentWord.charAt(i)}']`).classList.length==0)
            document.querySelector(`[data-key='${currentWord.charAt(i)}']`).classList.add(myData.charAt(i));
          if (document.querySelector(`[data-key='${currentWord.charAt(i)}']`).classList[0]=="Y" && myData.charAt(i)=='G'){
              document.querySelector(`[data-key='${currentWord.charAt(i)}']`).classList.remove('Y');
              document.querySelector(`[data-key='${currentWord.charAt(i)}']`).classList.add(myData.charAt(i));
          }
            await timer(400);
    
  }
    currentWord = "";
}
function checkAnswer(data){
  let answer = 0;
  for(let i=0;i<data.length;i++){
    if(data.charAt(i) == 'G'){
        answer ++;
    }  
  }
  return answer == size;
}

function onSignIn(googleUser) {
  var profile = googleUser.getBasicProfile();
  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
  console.log('Name: ' + profile.getName());
  console.log('Image URL: ' + profile.getImageUrl());
  console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
}

  

  function makeRows(rows, cols) {
    gameBoard.style.gridTemplateColumns = `repeat(${cols}, 1fr)`;
    gameBoard.style.gridTemplateRows =`repeat(${rows}, 1fr)`;
  }

  function handleKeyDown(e){
      if((e.key  >= 'a' && e.key <= 'z') || e.key == 'ç' || e.key == 'ı' || e.key == 'ö' || e.key == 'ü' || e.key == 'ş' || e.key == 'ğ')
        addLetter(e.key,size);
      if(e.key == 'Backspace')
        deleteLetter();
      if(e.key == 'Enter')
        checkWord(size);
  }

  addEventListener("keydown", handleKeyDown);
  //write a function

  
  
  async function shake(message){

      // Get the snackbar DIV
    var x = document.getElementById("snackbar");

  // Add the "show" class to DIV
    x.className = "show";
    x.innerText = message;
    console.log(wordSize)
    // After 3 seconds, remove the show class from DIV
    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 2000);
    for(let i=0;i<wordSize;i++){
      document.getElementById(currentRow*size+i+1).classList.add("animate__shake");
    }
    await timer(400);
    for(let i=0;i<wordSize;i++){
      document.getElementById(currentRow*size+i+1).classList.remove("animate__shake");
    }
  }



});
