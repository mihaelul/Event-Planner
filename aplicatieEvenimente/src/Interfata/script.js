document.addEventListener("DOMContentLoaded", function() {
    
    const butonCreare = document.getElementById("buton-creare");
    if(butonCreare) {
        butonCreare.addEventListener("click", function() {
            window.location.href = "evenimente.html";
        });
    }

    const btnAdauga = document.getElementById("btn-adauga-eveniment");
    const modalEveniment = document.getElementById("modal-eveniment");
    const btnInchideModal = document.getElementById("btn-inchide-modal");
    const formEveniment = document.getElementById("form-eveniment");

//adaugi eveniment
    if (btnAdauga && modalEveniment) {
        btnAdauga.addEventListener("click", function() {
            modalEveniment.classList.remove("modal-ascuns");
        });
    }
    if (btnInchideModal && modalEveniment) {
        btnInchideModal.addEventListener("click", function() {
            modalEveniment.classList.add("modal-ascuns");
        });
    }

    if (modalEveniment) {
        window.addEventListener("click", function(event) {
            if (event.target === modalEveniment) {
                modalEveniment.classList.add("modal-ascuns");
            }
        });
    }

    if (formEveniment) {
        formEveniment.addEventListener("submit", function(event) {
            event.preventDefault(); 
            
            alert("Evenimentul a fost salvat cu succes!");
            modalEveniment.classList.add("modal-ascuns"); 
            formEveniment.reset(); 
        });
    }

});
