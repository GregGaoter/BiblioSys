import { Action, configureStore, ThunkAction } from "@reduxjs/toolkit";
import bibliothequeReducer from "../store/slice/BibliothequeSlice";

export const store = configureStore({
  reducer: {
    bibliotheque: bibliothequeReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
export type AppThunk = ThunkAction<void, RootState, unknown, Action>;
