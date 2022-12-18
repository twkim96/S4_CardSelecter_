import { createSlice } from '@reduxjs/toolkit';

const initialStateValue = {
    id: localStorage.getItem("id"),
    pwd: localStorage.getItem("pwd"),
    name: localStorage.getItem("name")
}
export const userSlice = createSlice({
    name: "user",
    initialState: {value: initialStateValue},
    reducers: {
        login: (state, action) => {
            state.value = action.payload
        },
        logout: (state)=>{
            state.value = {id: "", pwd: "", name: ""}
        }
    },
});

export const { login, logout } = userSlice.actions;
export default userSlice.reducer;
