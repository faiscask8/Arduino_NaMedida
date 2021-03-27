package br.com.namedida.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.namedida.medida.controller.ConsultarMedidaController;
import br.com.namedida.model.MedidaModel;
import br.com.namedida.repository.entity.MedidaEntity;
import br.com.namedida.uteis.Uteis;

public class MedidaRepository {

	public static int TotalAlunosGlobal = 0;

	@Inject
	MedidaEntity medidaEntity;

	EntityManager entityManager;

	@Inject
	ConsultarMedidaController consultarMedidaController;

	@Inject
	MedidaModel medidaModel;

	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UMA NOVA MEDIDA
	 * 
	 * @param produtoModel
	 */
	public void SalvarNovoRegistro(MedidaModel medidaModel) {

		entityManager = Uteis.JpaEntityManager();
		medidaEntity = new MedidaEntity();
		medidaEntity.setCartao(medidaModel.getCartao());
		medidaEntity.setData(medidaModel.getData());
		medidaEntity.setQuantidade(medidaModel.getQuantidade());

		entityManager.persist(medidaEntity);

	}

	/***
	 * MÉTODO PARA CONSULTAR UM PRODUTO
	 * 
	 * @return
	 */
	public List<MedidaModel> GetMedidas() {

		List<MedidaModel> medidasModel = new ArrayList<MedidaModel>();

		entityManager = Uteis.JpaEntityManager();

		Query query = entityManager.createNamedQuery("MedidaEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<MedidaEntity> medidasEntity = (Collection<MedidaEntity>) query
				.getResultList();

		MedidaModel medidaModel = null;

		for (MedidaEntity medidaEntity : medidasEntity) {

			medidaModel = new MedidaModel();
			medidaModel.setCodigo(medidaEntity.getCodigo());
			medidaModel.setCartao(medidaEntity.getCartao());
			medidaModel.setData(medidaEntity.getData());
			medidaModel.setQuantidade(medidaEntity.getQuantidade());

			medidasModel.add(medidaModel);
		}

		return medidasModel;

	}

	/***
	 * CONSULTA UMA MEDIDA CADASTRADO PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	private MedidaEntity GetMedidas(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		return entityManager.find(MedidaEntity.class, codigo);
	}

	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * 
	 * @param medidaModel
	 */
	public void AlterarRegistro(MedidaModel medidaModel) {

		entityManager = Uteis.JpaEntityManager();

		MedidaEntity medidaEntity = this.GetMedidas(medidaModel.getCodigo());

		medidaEntity.setCartao(medidaModel.getCartao());

		entityManager.merge(medidaEntity);
	}

	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * 
	 * @param codigo
	 */
	public void ExcluirRegistro(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		MedidaEntity medidaEntity = this.GetMedidas(codigo);

		entityManager.remove(medidaEntity);
	}

	public void TotalAlunos() {

		entityManager = Uteis.JpaEntityManager();
		Query query = entityManager
				.createNativeQuery("SELECT data, SUM(quant) as Total FROM NAMEDIDA.medida where DATA = current_date");

		@SuppressWarnings("unchecked")
		List<Object[]> list = query.getResultList();
		Iterator<Object[]> ite = list.iterator();

		BigDecimal TotalHoje = BigDecimal.ZERO;
		System.out.println("total antes: " + medidaModel.getTotalhoje());

		while (ite.hasNext()) {
			Object[] result = (Object[]) ite.next();

			TotalHoje = (BigDecimal) result[1];

		}

		System.out.println("Valor big: " + TotalHoje);
		if (TotalHoje != null) {
			medidaModel.setTotalhoje(Integer.valueOf(TotalHoje.toString()));
			consultarMedidaController.setTotalAgora(Integer.valueOf(TotalHoje
					.toString()));
			TotalAlunosGlobal = Integer.valueOf(TotalHoje.toString());
		}

	}

	public void DiaHoje() {

		Date d = new Date();
		Calendar c = new GregorianCalendar();
		c.setTime(d);
		String nome = "";
		int dia = c.get(c.DAY_OF_WEEK);
		switch (dia) {
		case Calendar.SUNDAY:
			nome = "DOMINGO";
			break;
		case Calendar.MONDAY:
			nome = "SEGUNDA";
			break;
		case Calendar.TUESDAY:
			nome = "TERÇA";
			break;
		case Calendar.WEDNESDAY:
			nome = "QUARTA";
			break;
		case Calendar.THURSDAY:
			nome = "QUINTA";
			break;
		case Calendar.FRIDAY:
			nome = "SEXTA";
			break;
		case Calendar.SATURDAY:
			nome = "SÁBADO";
			break;
		}
		medidaModel.setDiahoje(nome);
		consultarMedidaController.setDiaHoje(nome);

	}
}