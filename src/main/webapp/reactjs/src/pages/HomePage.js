import React, {Component} from 'react';
import burgerbuild from "../assets/burgerbuild.gif";
import ButtonSection from "../components/ButtonSection";
import NavBar from "../components/NavBar";

class HomePage extends Component {
    render() {
        return (
                <div className="grid--container">
                    <NavBar/>
                    <main>
                        <img src={burgerbuild} alt="Make your burger"/>
                        {localStorage.getItem("User") ? (
                            <ButtonSection/>
                        ) : (
                            <a>Login to order</a>
                        )}

                    </main>
                    <footer>
                        All right
                    </footer>
                </div>
        );
    }
}

export default HomePage;