document.addEventListener("DOMContentLoaded", function() {
    const parametriUrl = new URLSearchParams(window.location.search);
    const numeEveniment = parametriUrl.get("nume");
    const modalServiciu = document.getElementById("modal-serviciu");
    const btnInchideModal = document.getElementById("btn-inchide-modal-serviciu");

    if (btnInchideModal) {
        btnInchideModal.addEventListener("click", () => {
            modalServiciu.classList.add("modal-ascuns");
        });
    }

    if (numeEveniment) {
        fetch(`http://localhost:8080/api/evenimente/${encodeURIComponent(numeEveniment)}`)
            .then(response => response.json())
            .then(ev => {
                document.getElementById("titlu-pagina").innerText = ev.nume ? ev.nume.toUpperCase() : "EVENIMENT";
                document.getElementById("data-pagina").innerText = ev.data || "-";
                document.getElementById("buget-pagina").innerText = `BUGET: ${ev.buget || 0} EURO`;

                const corpTabel = document.getElementById("corp-tabel-servicii");
                corpTabel.innerHTML = ""; 

                if (ev.servicii && ev.servicii.length > 0) {
                    ev.servicii.forEach(serviciu => {
                        const rand = document.createElement("tr");
                        rand.style.cursor = "pointer";

                        let textPretBucata = "-", textCantitate = "-";

                        if (serviciu.capacitate !== undefined) {
                            textCantitate = serviciu.capacitate + " persoane";
                        } else if (serviciu.pretOra !== undefined) {
                            textPretBucata = serviciu.pretOra + " €/oră";
                            textCantitate = serviciu.numarOre + " ore";
                        } else if (serviciu.pretBucata !== undefined) {
                            textPretBucata = serviciu.pretBucata + " €/meniu";
                            textCantitate = (ev.numarPersoane || 0) + " invitați"; 
                        }

                        rand.innerHTML = `
                            <td class="celula-alba celula-stanga">
                                <span class="icon-sterge" title="Șterge">⊗</span> ${serviciu.nume || '-'}
                            </td>
                            <td>${serviciu.prioritate || '-'}</td>
                            <td class="celula-alba">${textPretBucata}</td>
                            <td>${textCantitate}</td>
                            <td class="celula-alba">${serviciu.pret || 0} €</td>
                        `;

                        rand.addEventListener("click", function(event) {
                            
                            if(event.target.classList.contains('icon-sterge')) {
                                const numeServiciu = serviciu.nume;
                                
                                if (confirm(`Ești sigur că vrei să ștergi serviciul "${numeServiciu}"?`)) {
                                    fetch(`http://localhost:8080/api/evenimente/${encodeURIComponent(numeEveniment)}/servicii/${encodeURIComponent(numeServiciu)}`, {
                                        method: 'DELETE'
                                    })
                                    .then(response => response.text())
                                    .then(mesaj => {
                                        alert(mesaj);
                                        location.reload(); 
                                    })
                                    .catch(error => {
                                        console.error("Eroare la ștergere:", error);
                                        alert("Nu s-a putut șterge serviciul.");
                                    });
                                }
                                return; 
                            }

                            document.getElementById("modal-titlu-serviciu").innerText = serviciu.nume || "Fără nume";
                            document.getElementById("modal-prioritate").innerText = serviciu.prioritate || "-";
                            document.getElementById("modal-pret-total").innerText = serviciu.pret || 0;

                            let htmlExtra = "";
                            if (serviciu.capacitate !== undefined) {
                                htmlExtra += `<p><strong>Tip:</strong> Locație</p>`;
                                htmlExtra += `<p><strong>Tip Locație:</strong> ${serviciu.tipLocatie || '-'}</p>`;
                                htmlExtra += `<p><strong>Adresă:</strong> ${serviciu.adresa || '-'}</p>`;
                                htmlExtra += `<p><strong>Capacitate:</strong> ${serviciu.capacitate} persoane</p>`;
                            } else if (serviciu.pretOra !== undefined) {
                                htmlExtra += `<p><strong>Tip:</strong> Muzică</p>`;
                                htmlExtra += `<p><strong>Gen Muzical:</strong> ${serviciu.genMuzical || '-'}</p>`;
                                htmlExtra += `<p><strong>Durată:</strong> ${serviciu.numarOre} ore</p>`;
                            } else if (serviciu.pretBucata !== undefined) {
                                htmlExtra += `<p><strong>Tip:</strong> Catering</p>`;
                                htmlExtra += `<p><strong>Tip Meniu:</strong> ${serviciu.tipMeniu || '-'}</p>`;
                            } else {
                                htmlExtra += `<p><strong>Tip:</strong> Decorațiuni</p>`;
                                htmlExtra += `<p><strong>Tematică:</strong> ${serviciu.tematica || '-'}</p>`;
                            }

                            if(serviciu.contact) {
                                htmlExtra += `<p style="margin-top: 10px; color: #555;"><strong>Contact:</strong> ${serviciu.contact}</p>`;
                            }

                            document.getElementById("modal-detalii-extra").innerHTML = htmlExtra;
                            modalServiciu.classList.remove("modal-ascuns");
                        });

                        corpTabel.appendChild(rand);
                    });
                }

                let totalDinJava = ev.pretTotal || 0; 
                let bugetEvent = ev.buget || 0;
                let diferenta = bugetEvent - totalDinJava;

                document.getElementById("pret-total-pagina").innerText = `Preț Total: ${totalDinJava} €`;
                document.getElementById("diferenta-pagina").innerText = `Diferența buget: ${diferenta} €`;

            })
            .catch(error => console.error("Eroare:", error));
    }

    const modalAdaugare = document.getElementById("modal-adaugare-serviciu");
    const selectTip = document.getElementById("select-tip-serviciu");

    document.getElementById("btn-deschide-adaugare").addEventListener("click", () => {
        modalAdaugare.classList.remove("modal-ascuns");
    });

    document.getElementById("btn-inchide-adaugare").addEventListener("click", () => {
        modalAdaugare.classList.add("modal-ascuns");
        selectTip.value = "";
        document.querySelectorAll(".form-dinamic").forEach(form => form.style.display = "none");
    });

    selectTip.addEventListener("change", function() {
        document.querySelectorAll(".form-dinamic").forEach(form => form.style.display = "none");
        const tipAles = this.value;
        if(tipAles !== "") {
            document.getElementById(`form-${tipAles}`).style.display = "block";
        }
    });

    document.getElementById("btn-salveaza-serviciu").addEventListener("click", () => {
        const tipServiciu = document.getElementById("select-tip-serviciu").value;
        if (!tipServiciu) {
            alert("Te rugăm să alegi tipul serviciului!");
            return;
        }

        let dateServiciu = {
            tip: tipServiciu,
            nume: document.getElementById("input-nume-nou").value,
            prioritate: document.getElementById("input-prioritate-nou").value
        };

        if (tipServiciu === "locatie") {
            dateServiciu.capacitate = document.getElementById("loc-cap").value;
            dateServiciu.tipLocatie = document.getElementById("loc-tip").value;
            dateServiciu.adresa = document.getElementById("loc-adresa").value;
            dateServiciu.pret = document.getElementById("loc-pret").value;
            dateServiciu.contact = document.getElementById("loc-contact").value;
        } else if (tipServiciu === "muzica") {
            dateServiciu.genMuzical = document.getElementById("muz-gen").value;
            dateServiciu.numarOre = document.getElementById("muz-ore").value;
            dateServiciu.pretOra = document.getElementById("muz-pret").value;
            dateServiciu.contact = document.getElementById("muz-contact").value;
        } else if (tipServiciu === "catering") {
            dateServiciu.tipMeniu = document.getElementById("cat-tip").value;
            dateServiciu.pretBucata = document.getElementById("cat-pret").value;
            dateServiciu.contact = document.getElementById("cat-contact").value;
        } else if (tipServiciu === "decoratiuni") {
            dateServiciu.tematica = document.getElementById("dec-tematica").value;
            dateServiciu.pret = document.getElementById("dec-pret").value;
            dateServiciu.contact = document.getElementById("dec-contact").value;
        }

        fetch(`http://localhost:8080/api/evenimente/${encodeURIComponent(numeEveniment)}/servicii`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dateServiciu)
        })
        .then(response => response.text())
        .then(mesaj => {
            alert(mesaj);
            location.reload(); 
        })
        .catch(error => {
            console.error("Eroare la salvare:", error);
            alert("A apărut o eroare la salvarea serviciului.");
        });
    });
});