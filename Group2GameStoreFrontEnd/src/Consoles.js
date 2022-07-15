import { useState, useEffect } from 'react';

import ConsoleCard from './ConsoleCard.js';
import ConsoleForm from './ConsoleForm.js';

function Consoles() {

    const [consoles, setConsoles] = useState([]);
    const [showForm, setShowForm] = useState(false);
    const [scopedConsole, setScopedConsole] = useState({});
    const [error, setError] = useState();

    

    // function useInput(defaultValue) {
    //     const [value, setValue] = useState(defaultValue);

    //     function onChange(e) {
    //         setValue(e.target.value)
    //     }

    //     return {
    //         value,
    //         onChange,
    //     };
    // }


    useEffect(() => {
        fetch("http://localhost:8080/consoles")
        .then(r => r.json())
        .then(result => setConsoles(result))
        .catch(consoles.log)
    }, []);

    function addClick() {
        setScopedConsole({ id: 0, model: "", manufacturer: "", memoryAmount: "", processor: "", price: 0, quantity: 0});
        setShowForm(true);
    }

    // function fetchByManufacturer(evt){
    //     evt.preventDefault();

    //     console.log(evt.target.value);

    //     if (evt.target.value === "") {
    //         setConsoles([]);
    //     } else {
    //         fetch("http://localhost:8080/consoles/manufacturer?manufacturer" + evt.target.value)
    //             .then(response => response.json())
    //             .then(result => setConsoles(result))
    //             .catch(console.log);
    //     }
    //   }


    //   function handleChange(evt){
    //     const clone = {...console };
    //     clone[evt.target.name] = evt.target.value;
    //     //setConsole(clone);
    // }

    function handleChange(evt) {
        this.setState({value: evt.target.value});
      }
    
    function handleSubmit(evt) {
        alert('A name was submitted: ' + this.state.value);
        evt.preventDefault();
      }


    function notify({ action, console, error}) {

        if(error){
            setError(error);
            setShowForm(false);
            return;
        }


        switch (action) {
            case "add":
                setConsoles([...consoles, consoles]);
                break;
            case "edit":
                console.log("Edit has been called");
                setConsoles(console.map(c => {
                    if(c.Id === console.id) {
                        return console;
                    }
                    return c;
                }));
                break;
            case "edit-form":
                setShowForm(true);
                setScopedConsole(console);
                return;
            case "delete":
                console.log("Delete has been called");
                setConsoles(consoles.filter(c => c.Id !== console.id));
                break;
            default:
                console.log("Called notify with invaid action");
        }
        setError("");
        setShowForm(false);
    }

    if (showForm){
        return <ConsoleForm console={scopedConsole} notify={notify} />
    }

    return(
        <>
            {error && <div className="alert alert-danger">{error}</div>}

            <div>
                <h1 id='consoleTitle'>Consoles</h1>
                <button className="btn btn-primary" type="button" onClick={addClick}>Add a Console</button>
                {/* <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label htmlFor="manufacturer">Manufacturer</label>
                        <input type="text" id="manufacturer" name="manufacturer"
                        className="form-control"
                        value={this.state.value}
                        onChange={handleChange} 
                        />
                    </div>
                    <div>
                        <button className="btn btn-primary mr-3" type="submit">Search by manufacturer</button>
                    </div>
                </form> */}
                {/* <select name="esrbRating" onChange={fetchByEsrbRating}>
                    <option value="">Get Games by ESRB Rating</option>
                    <option value="Everyone">Everyone</option>
                    <option value="Everyone 10+">Everyone 10+</option>
                    <option value="Teen">Teen</option>
                    <option value="Mature 17+">Mature 17+</option>
                    <option value="Adults Only 18+">Adults Only 18+</option>
                    <option value="Rating Pending">Rating Pending</option>
                </select> */}
                <table id='consoles'>
                    <tbody>
                        <tr>
                            <th>Model</th>
                            <th>Manufacturer</th>
                            <th>Memory Amount</th>
                            <th>Processor</th>
                            <th>Price</th>
                            <th>Quantity</th>
                        </tr>
                    </tbody>
                    <tbody>
                        {consoles.map(c => <ConsoleCard key={c.Id} console={c} notify={notify} />)}
                    </tbody>
                </table>
            </div>
        </>
    )
}

export default Consoles;