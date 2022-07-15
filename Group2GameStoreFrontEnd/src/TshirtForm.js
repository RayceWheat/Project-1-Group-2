import { useState } from 'react';

function TshirtForm({tshirt: initialTshirt, notify}) {
    const [tshirt, setTshirt] = useState(initialTshirt);
    const isAdd = initialTshirt === 0;

    function handleChange(event) {
        const clone = { ...tshirt };
        clone[event.target.name] = event.target.value;
        setTshirt(clone);
    }

    function handleSubmit(e) {
        e.preventDefault();

        
        const url = `http://localhost:8080/tshirt`;
        const method = isAdd ? "POST" : "PUT";
        const expectedStatus = isAdd ? 201 : 204;

        const init = {
            method,
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            body: JSON.stringify(tshirt)
        };

        fetch(url, init)
        .then(response => {
            //console.log("fetched")
            if (response.status === expectedStatus) {
                if (isAdd) {
                    return response.json();
                } else {
                    return tshirt;
                }
            }
            return Promise.reject(`Didn't receive expected status: ${expectedStatus}`);
        })
        .then(result => notify({
            action: isAdd ? "Add" : "Edit",
            tshirt: result
        }))
        .catch(error => notify({ error: error }));
    }

    return (
        <>
            <h1>{tshirt.tshirtId > 0 ? "Edit" : "Add"} Tshirts</h1>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="tshirt">Tshirt</label>
                    <input type="text" id="tshirt" name="tshirt"
                        className="form-control"
                        value={tshirt.tshirt} onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <label htmlFor="color">Color</label>
                    <select name="color" value={tshirt.color} onChange={handleChange}>
                        <option value="red">Red</option>
                        <option value="gold">Gold</option>
                        <option value="camouflage">Camouflage</option>
                        <option value="green">Green</option>
                        <option value="orange">Orange</option>
                    </select>
                </div>
                <div className="mb-3">
                    <label htmlFor="size">Size</label>
                    <select name="size" value={tshirt.size} onChange={handleChange}>
                        <option value="s">small</option>
                        <option value="m">medium</option>
                        <option value="l">large</option>
                        <option value="xl">xlarge</option>
                        <option value="xs">xsmall</option>
                    </select>
                </div>
                <div className="mb-3">
                    <label htmlFor="description">description</label>
                    <input type="text" id="description" name="description"
                        className="form-control"
                        value={tshirt.description} onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <label htmlFor="price">price</label>
                    <input type="text" id="price" name="price"
                        className="form-control"
                        value={tshirt.price} onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <label htmlFor="quantity">quantity</label>
                    <input type="text" id="quantity" name="quantity"
                        className="form-control"
                        value={tshirt.quantity} onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <button id="saveButton" className="btn btn-primary mr-3" type="submit">Save</button>
                    <button className="btn btn-secondary" type="button" onClick={() => notify({ action: "cancel" })}>Cancel</button>
                </div>
            </form>
        </>
    )
}

export default TshirtForm;