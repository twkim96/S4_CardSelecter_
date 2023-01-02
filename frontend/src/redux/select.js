import { createSlice } from '@reduxjs/toolkit';

const initialStateValue = {
    customCard: "",
    customPath: "",
}

export const selectSlice = createSlice({
    name: "selectCard",
    initialState: {value: initialStateValue},
    reducers: {
        select: (state, action) => {
            state.value = action.payload
        }
    },
});

export const { select } = selectSlice.actions;
export default selectSlice.reducer;