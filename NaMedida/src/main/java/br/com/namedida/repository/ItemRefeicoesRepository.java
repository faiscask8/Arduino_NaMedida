package br.com.namedida.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.namedida.cardapio.controller.ConsultarItemCardapioController;
import br.com.namedida.dia.controller.ConsultarDiaController;
import br.com.namedida.medida.controller.ConsultarMedidaController;
import br.com.namedida.model.ItemCardapioModel;
import br.com.namedida.model.ItemRefeicoesModel;
import br.com.namedida.model.ProdutoModel;
import br.com.namedida.repository.entity.ItemRefeicoesEntity;
import br.com.namedida.repository.entity.ProdutoEntity;
import br.com.namedida.repository.entity.RefeicoesEntity;
import br.com.namedida.uteis.Uteis;

public class ItemRefeicoesRepository {

	@Inject
	ItemRefeicoesModel itemRefeicoesModel;

	@Inject
	ItemRefeicoesEntity itemRefeicoesEntity;

	@Produces
	private List<ItemRefeicoesModel> itensrefeicoes;

	EntityManager entityManager;

	private String path; // Caminho base

	private String pathToReportPackage;

	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UM NOVO PRODUTO NO CARDAPIO
	 * 
	 * @param itemRefeicoesModel
	 */
	public void SalvarNovoItemRefeicoes(ItemRefeicoesModel itemRefeicoesModel) {

		entityManager = Uteis.JpaEntityManager();

		for (ItemCardapioModel itemCardapioModel : ConsultarItemCardapioController.itemCardapiosTotal) {

			itemRefeicoesEntity = new ItemRefeicoesEntity();

			RefeicoesEntity refeicoesEntity = entityManager.find(
					RefeicoesEntity.class, RefeicoesRepository.RefeicaoGlobal);
			itemRefeicoesEntity.setRefeicoesEntity(refeicoesEntity);

			ProdutoEntity produtoEntity = entityManager.find(
					ProdutoEntity.class, itemCardapioModel.getProdutoModel()
							.getCodigo());
			itemRefeicoesEntity.setProdutoEntity(produtoEntity);

			itemRefeicoesEntity.setQt_quant(itemCardapioModel.getQt_quant());

			entityManager.persist(itemRefeicoesEntity);

		}

	}

	public String getPathToReportPackage() {
		this.path = this.getClass().getClassLoader().getResource("").getPath();
		this.pathToReportPackage = this.path + "br/com/namedida/reports/";

		return this.pathToReportPackage;
	}

	public String getPath() {
		return this.path;
	}

	/***
	 * METODO PARA CONSULTAR INTES REFEICAO
	 * 
	 * @return
	 */
	public List<ItemRefeicoesModel> GetItensRefeicao(Integer RefeicaoAtual) { //

		List<ItemRefeicoesModel> itensRefeicoesModel = new ArrayList<ItemRefeicoesModel>();

		entityManager = Uteis.JpaEntityManager();

		Query query = entityManager
				.createNamedQuery("ItemRefeicoesEntity.findItemRefeicoes");
		query.setParameter("idrefeicoes", RefeicaoAtual);

		@SuppressWarnings("unchecked")
		Collection<ItemRefeicoesEntity> ItensRefeicoesEntity = (Collection<ItemRefeicoesEntity>) query
				.getResultList();

		ItemRefeicoesModel itemRefeicoesModel = null;

		for (ItemRefeicoesEntity itemRefeicoesEntity : ItensRefeicoesEntity) {
			itemRefeicoesModel = new ItemRefeicoesModel();
			itemRefeicoesModel.setQt_quant(itemRefeicoesEntity.getQt_quant());

			ProdutoEntity produtoEntity = itemRefeicoesEntity
					.getProdutoEntity();
			ProdutoModel produtoModel = new ProdutoModel();
			produtoModel.setCodigo(produtoEntity.getCodigo());
			produtoModel.setDescricao(produtoEntity.getDescricao());
			itemRefeicoesModel.setProdutoModel(produtoModel);

			itensRefeicoesModel.add(itemRefeicoesModel);
		}

		return itensRefeicoesModel;
	}

	public void ImprimirItensRefeicoes() {
		itensrefeicoes = GetItensRefeicao(RefeicoesRepository.RefeicaoGlobal); // aqui
																				// vai
																				// o
																				// numero
																				// da
																				// refeicao
																				// atual

		try {

			System.out.println(getPathToReportPackage() + "Refeicoes.jrxml");
			System.out.println("Cardapio Relatorio: "
					+ ConsultarDiaController.CardapioDoDia);
			System.out.println("Cardapio Relatorio: "
					+ ConsultarMedidaController.TotaAlunos);

			Map<String, Object> parametros = new HashMap<>();
			parametros.put("cardapio", ConsultarDiaController.CardapioDoDia);
			parametros
					.put("quant_alunos", ConsultarMedidaController.TotaAlunos);

			JasperReport report = JasperCompileManager.compileReport(this
					.getPathToReportPackage() + "Refeicoes.jrxml");

			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(
					itensrefeicoes);

			JasperPrint print = JasperFillManager.fillReport(report,
					parametros, ds);

			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			HttpServletResponse response = (HttpServletResponse) externalContext
					.getResponse();

			externalContext.responseReset();
			externalContext.setResponseContentType("application/pdf");

			JasperExportManager.exportReportToPdfStream(print,
					response.getOutputStream());
			facesContext.getApplication().getStateManager()
					.saveView(facesContext);
			facesContext.renderResponse();
			facesContext.responseComplete();

			itensrefeicoes.clear();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
