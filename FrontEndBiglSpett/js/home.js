// vai alla pagina prenotazioni
let btnPrenotaSpettacolo = document.getElementById('btnPrenotaSpettacolo');

function goToPaginaPrenotazione(){
  window.location.replace('./bookForm.html');
}

btnPrenotaSpettacolo.addEventListener('click',goToPaginaPrenotazione)

//mostra elenco concerti
let btnElencoSpettacoli = document.getElementById('btnElencoSpettacoli');

function mostraTabellaSpettacoli(){
  fetch('http://localhost:8080/biglietteriaspettacoli/spettacoli')
  .then(response => response.json())
  .then(data => { 
      let table = `
          <tr>
              <th>Codice spettacolo</th>
              <th>Artista</th>
              <th>Data</th>
              <th>Orario</th>
              <th>Città</th>
              <th>Luogo</th>
              <th>Prezzo</th>
             
          </tr>
      `;
  
      for(let i = 0; i < data.length; i++) {
  
          table +=`
              <tr>
                  <td>${data[i].idSpettacolo}</td>
                  <td>${data[i].artista}</td>
                  <td>${data[i].dataSpettacolo}</td>
                  <td>ore 20.00</td>
                  <td>${data[i].citta}</td>
                  <td>${data[i].luogo}</td>
                  <td>${data[i].prezzo}</td>




                 
              </tr>
          `;
      }
      
      let tabella = document.getElementById('tableSpettacoli')
      tabella.classList.add('nascondi');
      tabella.innerHTML = table;
  })
}
  
btnElencoSpettacoli.addEventListener('click', mostraTabellaSpettacoli)

//mostra elenco biglietti
let btnElencoBiglietti = document.getElementById('btnElencoBiglietti');

function mostraTabellaBiglietti(){
  //recupero i dati dalla session storage
  let utConnessoJSON = localStorage.getItem("data");
  let utConnesso = JSON.parse(utConnessoJSON)

  //prendo i dati che mi mancano dal form
  let codCliente = utConnesso.codCliente;
  let nome = utConnesso.nome;
  let cognome = utConnesso.cognome;

  fetch('http://localhost:8080/biglietteriaspettacoli/biglietto/'+codCliente)
  .then(response => response.json())
  .then(data => {
    let BigliettiUtente = JSON.stringify(data);
    localStorage.setItem("Biglietti",BigliettiUtente);
      let table = `
      <tr>
        <th>Codice biglietto</th>
        <th>Nome</th>
        <th>Cognome</th>
        <th>Tipo pagamento</th>
        <th>Quantità biglietti</th>
        <th>Data dello spettacolo</th>
        <th>Artista</th>
        <th>Città</th>
        <th>Luogo evento</th>
        <th>Prezzo singolo</th>
        <th>Prezzo totale</th>
      </tr>
      `;
  
    for(let i = 0; i < data.length; i++) {
  
    table += `
      <tr>
        <td>${data[i].codOperazione}</td>
        <td>${nome}</td>
        <td>${cognome}</td>
        <td>${data[i].tipoPagamento}</td>
        <td>${data[i].qtBiglietti}</td>
        <td>${data[i].data}</td>
        <td>${data[i].artista}</td>
        <td>${data[i].citta}</td>
        <td>${data[i].luogo}</td>
        <td>${data[i].prezzo}€</td>
        <td>${data[i].prezzo * data[i].qtBiglietti}€</td>
      </tr>
        `;
    }
  
    document.getElementById('tableBiglietti').innerHTML = table;
  })
}

btnElencoBiglietti.addEventListener('click',mostraTabellaBiglietti)

let btnLogin = document.getElementById('btnLogin');

function goToLogin(){
  window.location.replace('./login.html');
}

btnLogin.addEventListener('click',goToLogin)

let btnModificaBiglietto = document.getElementById('btnModificaBiglietto');



btnModificaBiglietto.addEventListener('click',goToPaginaPrenotazione)