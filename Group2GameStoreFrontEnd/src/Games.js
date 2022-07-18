import { useState, useEffect } from 'react';
// import './Games.css';
import GameCard from './GameCard.js';
import GameForm from './GameForm.js';

function Games() {

    const [games, setGames] = useState([]);
    const [showForm, setShowForm] = useState(false);
    const [scopedGame, setScopedGame] = useState([]);
    const [error, setError] = useState();

    useEffect(() => {
        fetch("http://localhost:8080/games")
        .then(r => r.json())
        .then(result => setGames(result))
        .catch(console.log)
    }, []);
 
    function addClick() {
        setScopedGame({ id: 0, title: "", esrbRating: "", description: "", price: 0, studio: "", quantity: 0});
        setShowForm(true);
    }

    function fetchByTitle(event){       
        event.preventDefault();
        if(document.getElementById("title").value === ""){
            setGames([]);
        } else {
            fetch(`http://localhost:8080/games/title=${document.getElementById("title").value}`)
            .then(response => response.json())
            .then(result => setGames(result))
            .catch(error => console.log(error))
        }
    }

    function fetchByEsrbRating(evt){
        if (evt.target.value === ""){
            setGames([]);
        } else {
            fetch("http://localhost:8080/games/esrbRating=" + evt.target.value)
                .then(response => response.json())
                .then(result => setGames(result))
                .catch(console.log);
        }
    }

        function fetchByStudio(event){       
        event.preventDefault();
        if(document.getElementById("studio").value === ""){
            setGames([]);
        } else {
            fetch(`http://localhost:8080/games/studio=${document.getElementById("studio").value}`)
            .then(response => response.json())
            .then(result => setGames(result))
            .catch(error => console.log(error))
        }
    }



    function notify({ action, game, error }) {

        if(error){
            setError(error);
            setShowForm(false);
            return;
        }

        switch (action) {
            case "add":
                setGames([...games, game]);
                break;
            case "edit":
                console.log("Edit has been called");
                setGames(game.map(e => {
                    if(e.gameId === game.gameId) {
                        return game;
                    }
                    return e;
                }));
                break;
            case "edit-form":
                setShowForm(true);
                setScopedGame(game);
                return;
            case "delete":
                console.log("Delete has been called");
                setGames(games.filter(e => e.gameId !== game.gameId));
                break;
            default:
                console.log("Called notify with invaid action");
        }

        setError("");
        setShowForm(false);
    }

    if (showForm){
        return <GameForm game={scopedGame} notify={notify} />
    }

    return(
        <>
         {error && <div className="alert alert-danger">{error}</div>}

            <div>
                <h1 id='customerTitle'>Games</h1>
                <button className="btn btn-primary" type="button" onClick={addClick}>Add a Game</button>
                <form name="titleForm" onSubmit={fetchByTitle}>
                        <label htmlFor="title">Get Game by Title</label>
                        <input type="title" id="title" name="title" ></input>
                        <input type="submit" value="Submit" ></input>
                </form>
                <form name="titleForm" onSubmit={fetchByStudio}>
                        <label htmlFor="title">Get Game by Studio</label>
                        <input type="studio" id="studio" name="studio" ></input>
                        <input type="submit" value="Submit" ></input>
                </form>
                <select name="esrbRating" onChange={fetchByEsrbRating}>
                    <option value="">Get Games by ESRB Rating</option>
                    <option value="Everyone">Everyone</option>
                  {/* //  <option value="Everyone 10+">Everyone 10+</option> */}
                    <option value="Teen">Teen</option>
                    <option value="Mature">Mature</option>
                    <option value="Adults Only">Adults Only</option>
                    <option value="Rating Pending">Rating Pending</option>
                </select>
            </div>

            <tbody>
                <tr>
                    <th>Title</th>
                    <th>ESRB Rating</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Studio</th>
                    <th>quantity</th>
                </tr>
            </tbody>
            <tbody>
                {games.map(g => <GameCard key={g.gameId} game={g} notify={notify} />)}
            </tbody>
            
        </>
    )
}


export default Games;