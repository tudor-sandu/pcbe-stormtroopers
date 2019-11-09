import axios from "axios"

export function getItemsToBuy() {
    return new Promise((resolve,reject) => {

        return axios.get("http://localhost:8000/info").then(((resp)=>{
            console.log(resp.data.actiuni_cumparare);
            resolve(resp.data.actiuni_cumparare)
        }))
    })
}

export function getItemsToSell() {
    return new Promise((resolve,reject) => {

        return axios.get("http://localhost:8000/info").then(((resp)=>{
            console.log(resp.data.actiuni_vanzare);
            resolve(resp.data.actiuni_vanzare)
        }))
    })
}