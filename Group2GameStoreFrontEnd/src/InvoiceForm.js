import React, { useState } from 'react';


function InvoiceForm({ invoice: initialInvoice, notify }) {

    const [invoice, setInvoice] = useState(initialInvoice);
    const isAdd = initialInvoice.invoiceId === 0;

    function handleChange(evt) {
        const clone = { ...invoice };
        clone[evt.target.name] = evt.target.value;
        setInvoice(clone);
    }

    function handleSubmit(evt){
        evt.preventDefault();
        
        console.log("submit/save has been called");

        const url = "http://localhost:8080/invoice";
        const method = isAdd ? "POST" : "PUT";
        const expectedStatus = isAdd ? 201 : 204;

        console.log("after the fetch");

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
            <h1>{invoice.invoiceId > 0 ? "Edit" : "Add"} Invoice</h1>
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
                    <select name="state" value={invoice.state} onChange={handleChange}>
                        <option value="AL">Alabama</option>
                        <option value="AK">Alaska</option>
                        <option value="AZ">Arizona</option>
                        <option value="AR">Arkansas</option>
                        <option value="CA">California</option>
                        <option value="CO">Colorado</option>
                        <option value="CT">Connecticut</option>
                        <option value="DE">Delaware</option>
                        <option value="DC">District Of Columbia</option>
                        <option value="FL">Florida</option>
                        <option value="GA">Georgia</option>
                        <option value="HI">Hawaii</option>
                        <option value="ID">Idaho</option>
                        <option value="IL">Illinois</option>
                        <option value="IN">Indiana</option>
                        <option value="IA">Iowa</option>
                        <option value="KS">Kansas</option>
                        <option value="KY">Kentucky</option>
                        <option value="LA">Louisiana</option>
                        <option value="ME">Maine</option>
                        <option value="MD">Maryland</option>
                        <option value="MA">Massachusetts</option>
                        <option value="MI">Michigan</option>
                        <option value="MN">Minnesota</option>
                        <option value="MS">Mississippi</option>
                        <option value="MO">Missouri</option>
                        <option value="MT">Montana</option>
                        <option value="NE">Nebraska</option>
                        <option value="NV">Nevada</option>
                        <option value="NH">New Hampshire</option>
                        <option value="NJ">New Jersey</option>
                        <option value="NM">New Mexico</option>
                        <option value="NY">New York</option>
                        <option value="NC">North Carolina</option>
                        <option value="ND">North Dakota</option>
                        <option value="OH">Ohio</option>
                        <option value="OK">Oklahoma</option>
                        <option value="OR">Oregon</option>
                        <option value="PA">Pennsylvania</option>
                        <option value="RI">Rhode Island</option>
                        <option value="SC">South Carolina</option>
                        <option value="SD">South Dakota</option>
                        <option value="TN">Tennessee</option>
                        <option value="TX">Texas</option>
                        <option value="UT">Utah</option>
                        <option value="VT">Vermont</option>
                        <option value="VA">Virginia</option>
                        <option value="WA">Washington</option>
                        <option value="WV">West Virginia</option>
                        <option value="WI">Wisconsin</option>
                        <option value="WY">Wyoming</option>
                    </select>
                </div>
                <div className="mb-3">
                    <label htmlFor="zipCode">Zip Code</label>
                    <input type="text" id="zipCode" name="zipCode"
                        className="form-control"
                        value={invoice.zipCode} onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <label htmlFor="itemType">Item Type</label>
                    <select name="itemType" value={invoice.itemType} onChange={handleChange}>
                        <option value="Games">Games</option>
                        <option value="T-shirts">T-shirts</option>
                        <option value="Consoles">Consoles</option>
                    </select>
                </div>
                <div className="mb-3">
                    <label htmlFor="intemId">Item ID</label>
                    <input type="text" id="intemId" name="intemId"
                        className="form-control"
                        value={invoice.intemId} onChange={handleChange} />
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