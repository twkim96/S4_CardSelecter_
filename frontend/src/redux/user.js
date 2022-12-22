import { createSlice } from '@reduxjs/toolkit';

const initialStateValue = {
    id: localStorage.getItem("id"),
    name: localStorage.getItem("name"),
    jwt: `"Authorization": Bearer ${localStorage.getItem("bbs_access_token")}`
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
