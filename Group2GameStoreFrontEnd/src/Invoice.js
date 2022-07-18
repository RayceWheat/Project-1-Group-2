import { useState, useEffect } from 'react';
// import './Invoices.css';
import InvoiceCard from './InvoiceCard.js';
import InvoiceForm from './InvoiceForm.js';

function Invoice() {

    const [invoices, setInvoices] = useState([]);
    const [showForm, setShowForm] = useState(false);
    const [scopedInvoice, setScopedInvoice] = useState([]);
    const [error, setError] = useState();

    useEffect(() => {
        fetch("http://localhost:8080/invoice")
        .then(r => r.json())
        .then(result => setInvoices(result))
        .catch(console.log)
    }, []);
 
    function addClick() {
        setScopedInvoice({ id: 0, name: "", street: "", city: "", state: "", zipCode: "", itemType: "", itemId: 0, quantity: 0 });
        setShowForm(true);
    }

    function notify({ action, invoice, error }) {

        if(error){
            setError(error);
            setShowForm(false);
            return;
        }

        switch (action) {
            case "add":
                setInvoices([...invoices, invoice]);
                break;
            case "edit":
                console.log("Edit has been called");
                setInvoices(invoices.map(inv => {
                    if(inv.invoiceId === invoice.invoiceId) {
                        return invoice;
                    }
                    return inv;
                }));
                break;
            case "edit-form":
                setShowForm(true);
                setScopedInvoice(invoice);
                return;
            case "delete":
                console.log("Delete has been called");
                setInvoices(invoices.filter(e => e.invoiceId !== invoice.invoiceId));
                break;
            default:
                console.log("Called notify with invaid action");
        }

        setError("");
        setShowForm(false);
    }

    if (showForm){
        return <InvoiceForm invoice={scopedInvoice} notify={notify} />
    }

    return(
        <>
         
            <div id='buttonPanel' className="row mt-2">
                <h1 id='invoiceTitle'>Invoices</h1>
                <button className="btn btn-primary" type="button" onClick={addClick}>Purchase a product</button>
                <table id='invoices'>
                    <tbody>
                        <tr>
                            <th>Name</th>
                            <th>Street</th>
                            <th>City</th>
                            <th>State</th>
                            <th>Zip Code</th>
                            <th>Item Type</th>
                            <th>Item ID</th>
                            <th>Quantity</th>
                            <th>Total</th>
                        </tr>
                    </tbody>
                    <tbody>
                        {invoices.map(g => <InvoiceCard key={g.invoiceId} invoice={g} notify={notify} />)}
                    </tbody>
                </table>
            </div>
            {error && <div className="alert alert-danger">{error}</div>}
        </>
    )
}


export default Invoice;