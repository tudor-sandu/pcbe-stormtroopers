import axios from "axios";

let config = {
  headers: {
    "Access-Control-Allow-Origin": true,
    // "Content-Length": 0,
    "Content-Type": "text/plain"
  }
};

export function getItemsToBuy() {
  return new Promise((resolve, reject) => {
    return axios.get("http://localhost:8000/info", null, config).then(resp => {
      console.log(resp.data.actiuni_cumparare);
      resolve(resp.data.actiuni_cumparare);
    });
  });
}

export function getItemsToSell() {
  return new Promise((resolve, reject) => {
    return axios.get("http://localhost:8000/info", null, config).then(resp => {
      console.log(resp.data.actiuni_vanzare);
      resolve(resp.data.actiuni_vanzare);
    });
  });
}

export function sellStock(stock) {
  return new Promise((resolve, reject) => {
    return axios
      .put("http://localhost:8000/sell", stock, config)
      .then(() => {
        resolve();
      })
      .catch(err => {
        console.log(err);
        reject();
      });
  });
}
