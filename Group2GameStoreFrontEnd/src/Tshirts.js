import {useState, useEffect} from 'react';
import TshirtCard from './TshirtCard';
import TshirtForm from './TshirtForm';

function Tshirts() {
    const [tshirts, setTshirts] = useState([]);
    const [scopedTshirt, setScopedTshirt] = useState({});
    const [showForm, setShowForm] = useState(false);
    const [error, setError] = useState();
    
    useEffect(() => {
        fetch("http://localhost:8080/tshirt")
        .then(r => r.json())
        .then(result => setTshirts(result))
        .catch(console.log)
    }, []);

    function fetchByColor(e){
        if (e.target.value ==="") {
            setTshirts([]);
        } else {
            fetch("http://localhost:8080/tshirt?color=" + e.target.value)
                .then(response => response.json())
                .then(result => setTshirts(result))
                .catch(console.log);
        }
    }

    function fetchBySize(e){
        if (e.target.value ==="") {
            setTshirts([]);
        } else {
            fetch("http://localhost:8080/tshirt?size=" + e.target.value)
                .then(response => response.json())
                .then(result => setTshirts(result))
                .catch(console.log);
        }
    }
    
    function addClick() {
        setScopedTshirt({id:0, tshirt: "", color: "", size: "", description: "", price: 0, quantity:1});
        setShowForm(true);
    }

    function notify({action, tshirt, error}){
        if (error) {
            setError(error);
            setShowForm(false);
            return;
        }

        switch (action) {
            case "add":
                setTshirts([...tshirts, tshirt]);
                break;
            case "edit":
                setTshirts(tshirts.map(e => {
                    if (tshirt.tShirtId === e.tShirtId) {
                        return tshirt;
                    }
                    return e;
                }) )
                break;
            case "edit-form":
                setScopedTshirt(tshirt);
                setShowForm(true);
                return;
            case "delete":
                setTshirts(tshirts.filter(e=> tshirt.tShirtId !==  e.tShirtId))
                break;
        }
        setError("");
        //close the form
        setShowForm(false);
    }

    if (showForm) {
        return <TshirtForm tshirt={scopedTshirt} notify={notify} />
    }

    return(
        <>
        <div>
            <h1>Tshirts</h1>
            <button className="btn btn-primary" type="button" onClick={addClick}>Add a Tshirt</button>
            <select name="color" onChange={fetchByColor}>
                <option value="red">Red</option>
                <option value="gold">Gold</option>
                <option value="camouflage">Camouflage</option>
                <option value="green">Green</option>
                <option value="orange">Orange</option>
            </select>
            <select name="size" onChange={fetchBySize}>
                    <option value="s">small</option>
                    <option value="m">medium</option>
                    <option value="l">large</option>
                    <option value="xl">xlarge</option>
                    <option value="xs">xsmall</option>
            </select>

        </div>
        {error && <div className="alert alert-danger">{error}</div>}
        <div>
            <table id="tshirts">
                <tr>
                    <th>tshirt</th>
                    <th>color</th>
                    <th>size</th>
                    <th>description</th>
                    <th>price</th>
                    <th>quantity</th>
                </tr>
                <tbody>
                    {tshirts.map(res => <TshirtCard key={res.tshirtId} tshirt={res} notify={notify} />)}
                </tbody>
            </table>

        </div>
        </>
    )
}

export default Tshirts;