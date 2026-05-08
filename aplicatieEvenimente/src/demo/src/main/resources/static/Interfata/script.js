document.addEventListener("DOMContentLoaded", function() {
    

    const modalEveniment = document.getElementById("modal-eveniment");
    const btnAdauga = document.getElementById("btn-adauga-eveniment");
    const btnInchideModal = document.getElementById("btn-inchide-modal");
    const formEveniment = document.getElementById("form-eveniment");
    const corpTabel = document.querySelector(".tabel-evenimente tbody");


    function incarcaEvenimente() {
        if (!corpTabel) return; 


        fetch('http://localhost:8080/api/evenimente')
            .then(response => response.json()) 
            .then(evenimente => {
                corpTabel.innerHTML = "";
                
                evenimente.forEach(ev => {
                    const rand = document.createElement("tr");
                    rand.className = "rand-activ";
                    rand.innerHTML = `
                        <td class="celula-alba link-eveniment" onclick="window.location.href='detalii-eveniment.html?nume=${encodeURIComponent(ev.nume)}'">${ev.nume}</td>
                        <td>${ev.buget}</td>
                    `;
                    corpTabel.appendChild(rand);
                });
            })
            .catch(error => console.error("Eroare la încărcarea evenimentelor:", error));
    }

    incarcaEvenimente();


    if (btnAdauga && modalEveniment) {
        btnAdauga.addEventListener("click", () => modalEveniment.classList.remove("modal-ascuns"));
    }

    if (btnInchideModal && modalEveniment) {
        btnInchideModal.addEventListener("click", () => modalEveniment.classList.add("modal-ascuns"));
    }


    if (formEveniment) {
        formEveniment.addEventListener("submit", function(event) {
            event.preventDefault(); 
            const evenimentNou = {
                nume: document.getElementById("nume-ev").value,
                data: document.getElementById("data-ev").value,
                buget: parseFloat(document.getElementById("buget-ev").value),
                numarPersoane: 100 
            };


            fetch('http://localhost:8080/api/evenimente', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json' 
                },
                body: JSON.stringify(evenimentNou) 
            })
            .then(response => response.text())
            .then(mesaj => {

                alert(mesaj);
                modalEveniment.classList.add("modal-ascuns"); 
                formEveniment.reset(); 
                
                incarcaEvenimente(); 
            })
            .catch(error => console.error("Eroare la salvare:", error));
        });
    }
});