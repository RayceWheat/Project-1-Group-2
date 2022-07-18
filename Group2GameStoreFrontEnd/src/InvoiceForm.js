import React, { useState } from 'react';


function InvoiceForm({ invoice: initialInvoice, notify }) {

    const [invoice, setInvoice] = useState(initialInvoice);
    const isAdd = initialInvoice.id === 0;

    function handleChange(evt) {
        const clone = { ...invoice };
        clone[evt.target.name] = evt.target.value;
        setInvoice(clone);
    }

    function handleSubmit(evt){
        evt.preventDefault();
        
        console.log(invoice);
        console.log("submit/save has been called");

        const url = "http://localhost:8080/invoice";
        const method = isAdd ? "POST" : "PUT";
        const expectedStatus = isAdd ? 201 : 204;

        console.log(invoice);

        const init = {
            method,
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            body: JSON.stringify(invoice)
        };

        fetch(url, init)
        .then(response => {
            if (response.status === expectedStatus) {
                if (isAdd) {
                    return response.json();
                } else {
                    return invoice;
                }
            }
            return Promise.reject(`Didn't receive expected status: ${expectedStatus}`);
        })
        .then(result => notify({
            action: isAdd ? "add" : "edit",
            invoice: result
        }))
        .catch(error => notify({ error: error }));

    }

    return(
        <>
            <h1>{invoice.id > 0 ? "Edit" : "Add"} Invoice</h1>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="title">Name</label>
                    <input type="text" id="name" name="name"
                        className="form-control"
                        value={invoice.name} onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <label htmlFor="street">Street</label>
                    <input type="text" id="street" name="street"
                        className="form-control"
                        value={invoice.street} onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <label htmlFor="city">City</label>
                    <input type="text" id="city" name="city"
                        className="form-control"
                        value={invoice.city} onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <label htmlFor="state">State</label>
                    <input type="text" id="state" name="state"
                        className="form-control"
                        value={invoice.state} onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <label htmlFor="zipCode">Zip Code</label>
                    <input type="text" id="zipCode" name="zipCode"
                        className="form-control"
                        value={invoice.zipCode} onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <label htmlFor="itemType">Item Type</label>
                    <input type="text" id="itemType" name="itemType"
                        className="form-control"
                        value={invoice.itemType} onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <label htmlFor="itemId">Item ID</label>
                    <input type="text" id="itemId" name="itemId"
                        className="form-control"
                        value={invoice.itemId} onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <label htmlFor="quantity">Quantity</label>
                    <input type="text" id="quantity" name="quantity"
                        className="form-control"
                        value={invoice.quantity} onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <button className="btn btn-primary mr-3" type="submit">Save</button>
                    <button className="btn btn-secondary" type="button" onClick={() => notify({ action: "cancel" })}>Cancel</button>
                </div>
            </form>
        </>
    );
}

export default InvoiceForm;