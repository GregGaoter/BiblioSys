import { Action, configureStore, ThunkAction } from "@reduxjs/toolkit";
import bibliothequeReducer from "../store/slice/BibliothequeSlice";
import livreReducer from "../store/slice/LivreSlice";

export const store = configureStore({
  reducer: {
    bibliotheque: bibliothequeReducer,
    livre: livreReducer,
  },
  middleware: (getDefaultMiddleware) => getDefaultMiddleware({ serializableCheck: false }),
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
export type AppThunk = ThunkAction<void, RootState, unknown, Action>;
