import React, {useEffect, useState} from "react";
import {Container, Header, List, Dimmer, Loader} from "semantic-ui-react";

import StockListing from "../../components/StockListing";

const style = {
    h1: {
        marginTop: "3em"
    },
    h2: {
        margin: "4em 0em 2em"
    },
    h3: {
        marginTop: "2em",
        padding: "2em 0em"
    },
    last: {
        marginBottom: "300px"
    }
};

export default function Buy(props) {
    const [isLoading, setIsLoading] = useState(false);

    useEffect(() => {
        setIsLoading(true);
        fetch('http://localhost:8000/info').then((res) => {
            return res.text();
        }).then(function (data) {
            setIsLoading(false);
        }).catch(err => {
            setIsLoading(false)
        })
    }, []);

    return (
        <div>
            <Header
                as="h3"
                textAlign="center"
                style={style.h3}
                content="Stock Exchange Listings"
            />
            <Container>
                <Dimmer active={isLoading}>
                    <Loader/>
                </Dimmer>
                <List divided relaxed>
                    <StockListing
                        stockName="AAPL"
                        stockPrice="100$"
                        stockVolume="23.55"
                    />
                    <StockListing
                        stockName="AAPL"
                        stockPrice="100.23$"
                        stockVolume="55"
                    />
                    <StockListing
                        stockName="AAPL"
                        stockPrice="100.43$"
                        stockVolume="90"
                    />
                </List>
            </Container>
        </div>
    );
}
