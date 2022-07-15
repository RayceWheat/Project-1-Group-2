function InvoiceCard({ invoice, notify }) {

    function handleDelete(){
        console.log("Delete has been called");
        fetch(`http://localhost:8080/invoices/${invoice.invoiceId}`, {method: "DELETE"})
            .then(() => notify({ action: "delete", invoices: invoice}))
            .catch(error => notify({ action: "delete", error: error}));
    }

    return(
        <tr key={invoice.name}>
            <td>{invoice.street}</td>
            <td>{invoice.city}</td>
            <td>{invoice.state}</td>
            <td>{invoice.zipCode}</td>
            <td>{invoice.itemType}</td>
            <td>{invoice.itemId}</td>
            <td>{invoice.quantity}</td>
            <td>
                <button id="deleteButton" className="btn btn-danger mr-3" type="button" onClick={handleDelete}>Delete</button>
                <button id="editButton" className="btn btn-secondary" type="button" onClick={() => notify({ action: "edit-form", invoice: invoice })}>Edit</button>
            </td>
        </tr>
    );
}



export default InvoiceCard;