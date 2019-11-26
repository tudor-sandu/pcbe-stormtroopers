import React, {useEffect, useState} from 'react';
import {Container, Dimmer, Header, List, Loader} from "semantic-ui-react";
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

export default function Sell(props) {
    const [isLoading, setIsLoading] = useState(false);
    const [stocks, setStocks] = useState([]);

    useEffect(() => {
        setIsLoading(true);
        fetch('http://localhost:8000/info').then(async (res) => {
            return setStocks(JSON.parse(await res.text()).actiuni_cumparare);
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
                    {stocks.map((stock) => {
                        return (<StockListing
                            stockName={stock.name}
                            stockPrice={stock.value}
                            stockVolume={stock.volume}
                        />)
                    })}
                </List>
            </Container>
        </div>
    );
}