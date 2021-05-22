import { configureStore } from "@reduxjs/toolkit";
import bibliothequeReducer from "../store/slice/BibliothequeSlice";

export const store = configureStore({
  reducer: {
    bibliotheque: bibliothequeReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
