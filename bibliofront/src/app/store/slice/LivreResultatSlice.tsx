import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { defaultValue, ILivreResultat } from "../../model/LivreResultatModel";
import type { AppThunk, RootState } from "../store";

interface LivreResultatState {
  entity: ILivreResultat;
  entities: ReadonlyArray<ILivreResultat>;
  loading: boolean;
  updating: boolean;
  updateSuccess: boolean;
  errorMessage: string | undefined;
}

const initialState: Readonly<LivreResultatState> = {
  entity: defaultValue,
  entities: [],
  loading: false,
  updating: false,
  updateSuccess: false,
  errorMessage: undefined,
};

// Reducer

const livreResultatSlice = createSlice({
  name: "livreResultat",
  initialState,
  reducers: {
    requestGetEntityEntities: (state) => {
      state.loading = true;
      state.updateSuccess = false;
      state.errorMessage = undefined;
    },
    failure: (state, action) => {
      state.loading = false;
      state.updating = false;
      state.updateSuccess = false;
      state.errorMessage = action.payload;
    },
    successGetEntity: (state, action) => {
      state.entity = action.payload.data;
      state.loading = false;
    },
    successGetEntities: (state, action) => {
      state.entities = action.payload.data;
      state.loading = false;
    },
    resetState: (state) => {
      state.entity = defaultValue;
      state.entities = [];
      state.loading = false;
      state.updating = false;
      state.updateSuccess = false;
      state.errorMessage = undefined;
    },
  },
});

export const baseUrl = "/livre/resultat";

const { requestGetEntityEntities, failure, successGetEntity, successGetEntities, resetState } =
  livreResultatSlice.actions;

// Actions

export const getEntity =
  (id: number): AppThunk =>
  async (dispatch: any) => {
    dispatch(requestGetEntityEntities);
    try {
      await axios.get<ILivreResultat>(`${baseUrl}/${id}`).then((response) => dispatch(successGetEntity(response)));
    } catch (e) {
      dispatch(failure(e.message));
    }
  };

export const getEntities = (): AppThunk => async (dispatch: any) => {
  dispatch(requestGetEntityEntities);
  try {
    await axios.get<ILivreResultat>(`${baseUrl}/all`).then((response) => dispatch(successGetEntities(response)));
  } catch (e) {
    dispatch(failure(e.message));
  }
};

export const getEntitiesByRayonId =
  (id: number): AppThunk =>
  async (dispatch: any) => {
    dispatch(requestGetEntityEntities);
    try {
      await axios
        .get<ILivreResultat>(`${baseUrl}/rayon/${id}`)
        .then((response) => dispatch(successGetEntities(response)));
    } catch (e) {
      dispatch(failure(e.message));
    }
  };

export const getEntitiesByGenreId =
  (id: number): AppThunk =>
  async (dispatch: any) => {
    dispatch(requestGetEntityEntities);
    try {
      await axios
        .get<ILivreResultat>(`${baseUrl}/genre/${id}`)
        .then((response) => dispatch(successGetEntities(response)));
    } catch (e) {
      dispatch(failure(e.message));
    }
  };

export const reset = () => (dispatch: any) => {
  dispatch(resetState);
};

// Selectors

export const entity = (state: RootState) => state.livreResultat.entity;
export const entities = (state: RootState) => state.livreResultat.entities;
export const loading = (state: RootState) => state.livreResultat.loading;
export const updating = (state: RootState) => state.livreResultat.updating;
export const updateSuccess = (state: RootState) => state.livreResultat.updateSuccess;
export const errorMessage = (state: RootState) => state.livreResultat.errorMessage;

export default livreResultatSlice.reducer;
